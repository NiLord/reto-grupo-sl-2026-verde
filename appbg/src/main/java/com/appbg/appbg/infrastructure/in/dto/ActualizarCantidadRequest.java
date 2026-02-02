package com.appbg.appbg.infrastructure.in.dto;

public class ActualizarCantidadRequest {
  private Long productoId;
  private Integer cantidad;

  public ActualizarCantidadRequest() {
  }

  public ActualizarCantidadRequest(Long productoId, Integer cantidad) {
    this.productoId = productoId;
    this.cantidad = cantidad;
  }

  public Long getProductoId() {
    return productoId;
  }

  public void setProductoId(Long productoId) {
    this.productoId = productoId;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }
}
