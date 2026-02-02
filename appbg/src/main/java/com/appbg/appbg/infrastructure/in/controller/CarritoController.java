package com.appbg.appbg.infrastructure.in.controller;

import com.appbg.appbg.domain.model.Carrito;
import com.appbg.appbg.domain.port.in.CarritoUseCase;
import com.appbg.appbg.infrastructure.in.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

  private final CarritoUseCase carritoUseCase;

  public CarritoController(CarritoUseCase carritoUseCase) {
    this.carritoUseCase = carritoUseCase;
  }

  @GetMapping
  public ResponseEntity<CarritoResponse> obtenerCarrito() {
    Carrito carrito = carritoUseCase.obtenerCarrito();
    CarritoResponse response = new CarritoResponse(
        carrito.getItems(),
        carrito.getTotal(),
        carrito.getTotalItems());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/agregar")
  public ResponseEntity<CarritoResponse> agregarProducto(@RequestBody AgregarProductoRequest request) {
    Carrito carrito = carritoUseCase.agregarProducto(request.getProductoId(), request.getCantidad());
    CarritoResponse response = new CarritoResponse(
        carrito.getItems(),
        carrito.getTotal(),
        carrito.getTotalItems());
    return ResponseEntity.ok(response);
  }

  @PutMapping("/actualizar")
  public ResponseEntity<CarritoResponse> actualizarCantidad(@RequestBody ActualizarCantidadRequest request) {
    Carrito carrito = carritoUseCase.actualizarCantidad(request.getProductoId(), request.getCantidad());
    CarritoResponse response = new CarritoResponse(
        carrito.getItems(),
        carrito.getTotal(),
        carrito.getTotalItems());
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/remover/{productoId}")
  public ResponseEntity<CarritoResponse> removerProducto(@PathVariable Long productoId) {
    Carrito carrito = carritoUseCase.removerProducto(productoId);
    CarritoResponse response = new CarritoResponse(
        carrito.getItems(),
        carrito.getTotal(),
        carrito.getTotalItems());
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/limpiar")
  public ResponseEntity<Void> limpiarCarrito() {
    carritoUseCase.limpiarCarrito();
    return ResponseEntity.ok().build();
  }
}
