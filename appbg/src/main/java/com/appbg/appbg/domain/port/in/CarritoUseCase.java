package com.appbg.appbg.domain.port.in;

import com.appbg.appbg.domain.model.Carrito;

public interface CarritoUseCase {
  Carrito agregarProducto(Long productoId, Integer cantidad);

  Carrito actualizarCantidad(Long productoId, Integer cantidad);

  Carrito removerProducto(Long productoId);

  Carrito obtenerCarrito();

  void limpiarCarrito();
}
