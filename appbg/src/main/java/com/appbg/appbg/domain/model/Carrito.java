package com.appbg.appbg.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carrito {
  private List<ItemCarrito> items;
  private BigDecimal total;

  public Carrito() {
    this.items = new ArrayList<>();
    this.total = BigDecimal.ZERO;
  }

  public void agregarProducto(Producto producto, Integer cantidad) {
    if (!producto.tieneStockSuficiente(cantidad)) {
      throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre());
    }

    Optional<ItemCarrito> itemExistente = items.stream()
        .filter(item -> item.getProductoId().equals(producto.getId()))
        .findFirst();

    if (itemExistente.isPresent()) {
      ItemCarrito item = itemExistente.get();
      item.incrementarCantidad(cantidad);
    } else {
      ItemCarrito nuevoItem = new ItemCarrito(
          producto.getId(),
          producto.getNombre(),
          producto.getPrecio(),
          cantidad);
      items.add(nuevoItem);
    }

    recalcularTotal();
  }

  public void actualizarCantidad(Long productoId, Integer nuevaCantidad) {
    ItemCarrito item = items.stream()
        .filter(i -> i.getProductoId().equals(productoId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado en el carrito"));

    if (nuevaCantidad == 0) {
      items.remove(item);
    } else {
      item.setCantidad(nuevaCantidad);
    }

    recalcularTotal();
  }

  public void removerProducto(Long productoId) {
    items.removeIf(item -> item.getProductoId().equals(productoId));
    recalcularTotal();
  }

  public void limpiar() {
    items.clear();
    total = BigDecimal.ZERO;
  }

  private void recalcularTotal() {
    total = items.stream()
        .map(ItemCarrito::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public boolean estaVacio() {
    return items.isEmpty();
  }

  public int getTotalItems() {
    return items.stream()
        .mapToInt(ItemCarrito::getCantidad)
        .sum();
  }

  // Getters
  public List<ItemCarrito> getItems() {
    return new ArrayList<>(items);
  }

  public BigDecimal getTotal() {
    return total;
  }
}
