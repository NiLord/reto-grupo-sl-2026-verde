package com.appbg.appbg.aplications.service;

import com.appbg.appbg.domain.model.Carrito;
import com.appbg.appbg.domain.model.Producto;
import com.appbg.appbg.domain.port.in.CarritoUseCase;
import com.appbg.appbg.domain.port.out.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class CarritoService implements CarritoUseCase {

  private final ProductoRepository productoRepository;
  private Carrito carrito;

  public CarritoService(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
    this.carrito = new Carrito();
  }

  @Override
  public Carrito agregarProducto(Long productoId, Integer cantidad) {
    Producto producto = productoRepository.buscarPorId(productoId)
        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

    carrito.agregarProducto(producto, cantidad);
    return carrito;
  }

  @Override
  public Carrito actualizarCantidad(Long productoId, Integer cantidad) {
    carrito.actualizarCantidad(productoId, cantidad);
    return carrito;
  }

  @Override
  public Carrito removerProducto(Long productoId) {
    carrito.removerProducto(productoId);
    return carrito;
  }

  @Override
  public Carrito obtenerCarrito() {
    return carrito;
  }

  @Override
  public void limpiarCarrito() {
    carrito.limpiar();
  }
}
