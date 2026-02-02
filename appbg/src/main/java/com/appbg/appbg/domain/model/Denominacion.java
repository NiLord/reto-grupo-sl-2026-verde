package com.appbg.appbg.domain.model;

import java.math.BigDecimal;

public class Denominacion {
  private Long id;
  private BigDecimal valor;
  private TipoDenominacion tipo;
  private Integer cantidadDisponible;

  public Denominacion() {
  }

  public Denominacion(Long id, BigDecimal valor, TipoDenominacion tipo, Integer cantidadDisponible) {
    this.id = id;
    this.valor = valor;
    this.tipo = tipo;
    this.cantidadDisponible = cantidadDisponible;
  }

  public Denominacion(BigDecimal valor, TipoDenominacion tipo, Integer cantidadDisponible) {
    this.valor = valor;
    this.tipo = tipo;
    this.cantidadDisponible = cantidadDisponible;
  }

  public boolean tieneDisponibilidad() {
    return this.cantidadDisponible != null && this.cantidadDisponible > 0;
  }

  public boolean tieneDisponibilidadSuficiente(Integer cantidad) {
    return this.cantidadDisponible != null && this.cantidadDisponible >= cantidad;
  }

  public void reducirCantidad(Integer cantidad) {
    if (!tieneDisponibilidadSuficiente(cantidad)) {
      throw new IllegalStateException("Cantidad insuficiente para la denominación: " + this.valor);
    }
    this.cantidadDisponible -= cantidad;
  }

  public void incrementarCantidad(Integer cantidad) {
    if (cantidad <= 0) {
      throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
    }
    this.cantidadDisponible += cantidad;
  }

  public BigDecimal calcularTotal() {
    return this.valor.multiply(BigDecimal.valueOf(this.cantidadDisponible));
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public TipoDenominacion getTipo() {
    return tipo;
  }

  public void setTipo(TipoDenominacion tipo) {
    this.tipo = tipo;
  }

  public Integer getCantidadDisponible() {
    return cantidadDisponible;
  }

  public void setCantidadDisponible(Integer cantidadDisponible) {
    this.cantidadDisponible = cantidadDisponible;
  }
}
