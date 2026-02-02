package com.appbg.appbg.domain.model;

import java.math.BigDecimal;

public class ItemCarrito {
  private Long productoId;
  private String nombreProducto;
  private BigDecimal precioUnitario;
  private Integer cantidad;
  private BigDecimal subtotal;

  public ItemCarrito() {
  }

  public ItemCarrito(Long productoId, String nombreProducto, BigDecimal precioUnitario, Integer cantidad) {
    this.productoId = productoId;
    this.nombreProducto = nombreProducto;
    this.precioUnitario = precioUnitario;
    this.cantidad = cantidad;
    this.subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
  }

  public void incrementarCantidad(Integer cantidad) {
    this.cantidad += cantidad;
    recalcularSubtotal();
  }

  public void decrementarCantidad(Integer cantidad) {
    if (this.cantidad - cantidad < 0) {
      throw new IllegalArgumentException("No se puede reducir la cantidad por debajo de 0");
    }
    this.cantidad -= cantidad;
    recalcularSubtotal();
  }

  public void setCantidad(Integer cantidad) {
    if (cantidad < 0) {
      throw new IllegalArgumentException("La cantidad no puede ser negativa");
    }
    this.cantidad = cantidad;
    recalcularSubtotal();
  }

  private void recalcularSubtotal() {
    this.subtotal = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
  }

  // Getters y Setters
  public Long getProductoId() {
    return productoId;
  }

  public void setProductoId(Long productoId) {
    this.productoId = productoId;
  }

  public String getNombreProducto() {
    return nombreProducto;
  }

  public void setNombreProducto(String nombreProducto) {
    this.nombreProducto = nombreProducto;
  }

  public BigDecimal getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(BigDecimal precioUnitario) {
    this.precioUnitario = precioUnitario;
    recalcularSubtotal();
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public BigDecimal getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
  }
}
