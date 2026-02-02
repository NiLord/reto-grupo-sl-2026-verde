package com.appbg.appbg.aplications.service;

import com.appbg.appbg.domain.model.*;
import com.appbg.appbg.domain.port.in.CarritoUseCase;
import com.appbg.appbg.domain.port.in.PagoUseCase;
import com.appbg.appbg.domain.port.out.DenominacionRepository;
import com.appbg.appbg.domain.port.out.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PagoService implements PagoUseCase {

  private final DenominacionRepository denominacionRepository;
  private final CarritoUseCase carritoUseCase;
  private final ProductoRepository productoRepository;

  public PagoService(DenominacionRepository denominacionRepository,
      CarritoUseCase carritoUseCase,
      ProductoRepository productoRepository) {
    this.denominacionRepository = denominacionRepository;
    this.carritoUseCase = carritoUseCase;
    this.productoRepository = productoRepository;
  }

  @Override
  public List<Denominacion> obtenerDenominaciones() {
    return denominacionRepository.listarDenominaciones();
  }

  @Override
  public ResultadoPago procesarPago(Map<Long, Integer> denominacionesInsertadas) {
    Carrito carrito = carritoUseCase.obtenerCarrito();

    if (carrito.estaVacio()) {
      return new ResultadoPago(
          BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
          new ArrayList<>(), false, "El carrito está vacío");
    }

    // Obtener todas las denominaciones
    List<Denominacion> todasDenominaciones = denominacionRepository.listarDenominaciones();
    Map<Long, Denominacion> mapaDenominaciones = todasDenominaciones.stream()
        .collect(Collectors.toMap(Denominacion::getId, d -> d));

    // Calcular monto insertado
    BigDecimal montoInsertado = denominacionesInsertadas.entrySet().stream()
        .map(entry -> {
          Denominacion denom = mapaDenominaciones.get(entry.getKey());
          return denom.getValor().multiply(BigDecimal.valueOf(entry.getValue()));
        })
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal total = carrito.getTotal();

    if (montoInsertado.compareTo(total) < 0) {
      return new ResultadoPago(
          total, montoInsertado, BigDecimal.ZERO,
          new ArrayList<>(), false, "Monto insuficiente");
    }

    BigDecimal cambio = montoInsertado.subtract(total);

    // Agregar denominaciones insertadas al sistema
    for (Map.Entry<Long, Integer> entry : denominacionesInsertadas.entrySet()) {
      Denominacion denom = mapaDenominaciones.get(entry.getKey());
      int nuevaCantidad = denom.getCantidadDisponible() + entry.getValue();
      denominacionRepository.actualizarCantidad(entry.getKey(), nuevaCantidad);
      denom.setCantidadDisponible(nuevaCantidad);
    }

    // Calcular cambio con algoritmo greedy
    List<ResultadoPago.DenominacionCambio> cambioDetallado = calcularCambio(
        cambio, todasDenominaciones);

    if (cambioDetallado == null) {
      // Revertir las denominaciones insertadas
      for (Map.Entry<Long, Integer> entry : denominacionesInsertadas.entrySet()) {
        Denominacion denom = mapaDenominaciones.get(entry.getKey());
        int cantidadOriginal = denom.getCantidadDisponible() - entry.getValue();
        denominacionRepository.actualizarCantidad(entry.getKey(), cantidadOriginal);
      }

      return new ResultadoPago(
          total, montoInsertado, cambio,
          new ArrayList<>(), false, "No hay suficientes denominaciones para dar cambio");
    }

    // Actualizar inventario de denominaciones después de dar cambio
    for (ResultadoPago.DenominacionCambio dc : cambioDetallado) {
      Denominacion denom = todasDenominaciones.stream()
          .filter(d -> d.getValor().compareTo(dc.getValor()) == 0)
          .findFirst()
          .orElseThrow();

      int nuevaCantidad = denom.getCantidadDisponible() - dc.getCantidad();
      denominacionRepository.actualizarCantidad(denom.getId(), nuevaCantidad);
    }

    // Reducir stock de productos
    for (ItemCarrito item : carrito.getItems()) {
      Producto producto = productoRepository.buscarPorId(item.getProductoId())
          .orElseThrow();
      producto.reducirStock(item.getCantidad());
    }

    // Limpiar carrito
    carritoUseCase.limpiarCarrito();

    return new ResultadoPago(
        total, montoInsertado, cambio,
        cambioDetallado, true, "Pago procesado exitosamente");
  }

  private List<ResultadoPago.DenominacionCambio> calcularCambio(
      BigDecimal cambio, List<Denominacion> denominaciones) {
    List<ResultadoPago.DenominacionCambio> resultado = new ArrayList<>();

    // Ordenar denominaciones de mayor a menor
    List<Denominacion> denoms = new ArrayList<>(denominaciones);
    denoms.sort((a, b) -> b.getValor().compareTo(a.getValor()));

    BigDecimal restante = cambio;

    for (Denominacion denom : denoms) {
      if (restante.compareTo(BigDecimal.ZERO) == 0) {
        break;
      }

      if (denom.getCantidadDisponible() == 0) {
        continue;
      }

      int cantidadNecesaria = restante.divide(denom.getValor(), 0, BigDecimal.ROUND_DOWN).intValue();
      int cantidadDisponible = denom.getCantidadDisponible();
      int cantidadAUsar = Math.min(cantidadNecesaria, cantidadDisponible);

      if (cantidadAUsar > 0) {
        resultado.add(new ResultadoPago.DenominacionCambio(
            denom.getValor(), cantidadAUsar, denom.getTipo()));
        restante = restante.subtract(denom.getValor().multiply(BigDecimal.valueOf(cantidadAUsar)));
      }
    }

    // Si no se pudo dar el cambio exacto
    if (restante.compareTo(BigDecimal.ZERO) > 0) {
      return null;
    }

    return resultado;
  }
}
