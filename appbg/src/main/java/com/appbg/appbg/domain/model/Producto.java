package com.appbg.appbg.domain.model;

import java.math.BigDecimal;
public class Producto {
  private Long id;
  private String nombre;
  private BigDecimal precio;
  private Integer cantidadDisponible;

  public Producto() {
  }

  public Producto(Long id, String nombre, BigDecimal precio, Integer cantidadDisponible) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.cantidadDisponible = cantidadDisponible;
  }

  public Producto(String nombre, BigDecimal precio, Integer cantidadDisponible) {
    this.nombre = nombre;
    this.precio = precio;
    this.cantidadDisponible = cantidadDisponible;
  }

  // Métodos de negocio
  public boolean tieneStock() {
    return this.cantidadDisponible != null && this.cantidadDisponible > 0;
  }

  public boolean tieneStockSuficiente(Integer cantidad) {
    return this.cantidadDisponible != null && this.cantidadDisponible >= cantidad;
  }

  public void reducirStock(Integer cantidad) {
    if (!tieneStockSuficiente(cantidad)) {
      throw new IllegalStateException("Stock insuficiente para el producto: " + this.nombre);
    }
    this.cantidadDisponible -= cantidad;
  }

  public void incrementarStock(Integer cantidad) {
    if (cantidad <= 0) {
      throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
    }
    this.cantidadDisponible += cantidad;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public BigDecimal getPrecio() {
    return precio;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
  }

  public Integer getCantidadDisponible() {
    return cantidadDisponible;
  }

  public void setCantidadDisponible(Integer cantidadDisponible) {
    this.cantidadDisponible = cantidadDisponible;
  }
}