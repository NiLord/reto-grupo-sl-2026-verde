package com.appbg.appbg.infrastructure.in.dto;

public class AgregarProductoRequest {
  private Long productoId;
  private Integer cantidad;

  public AgregarProductoRequest() {
  }

  public AgregarProductoRequest(Long productoId, Integer cantidad) {
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
