package com.appbg.appbg.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResultadoPago {
  private BigDecimal totalAPagar;
  private BigDecimal montoInsertado;
  private BigDecimal cambio;
  private List<DenominacionCambio> cambioDetallado;
  private boolean exitoso;
  private String mensaje;

  public ResultadoPago() {
    this.cambioDetallado = new ArrayList<>();
  }

  public ResultadoPago(BigDecimal totalAPagar, BigDecimal montoInsertado, BigDecimal cambio,
      List<DenominacionCambio> cambioDetallado, boolean exitoso, String mensaje) {
    this.totalAPagar = totalAPagar;
    this.montoInsertado = montoInsertado;
    this.cambio = cambio;
    this.cambioDetallado = cambioDetallado;
    this.exitoso = exitoso;
    this.mensaje = mensaje;
  }

  public static class DenominacionCambio {
    private BigDecimal valor;
    private Integer cantidad;
    private TipoDenominacion tipo;

    public DenominacionCambio() {
    }

    public DenominacionCambio(BigDecimal valor, Integer cantidad, TipoDenominacion tipo) {
      this.valor = valor;
      this.cantidad = cantidad;
      this.tipo = tipo;
    }

    public BigDecimal getValor() {
      return valor;
    }

    public void setValor(BigDecimal valor) {
      this.valor = valor;
    }

    public Integer getCantidad() {
      return cantidad;
    }

    public void setCantidad(Integer cantidad) {
      this.cantidad = cantidad;
    }

    public TipoDenominacion getTipo() {
      return tipo;
    }

    public void setTipo(TipoDenominacion tipo) {
      this.tipo = tipo;
    }
  }

  // Getters y Setters
  public BigDecimal getTotalAPagar() {
    return totalAPagar;
  }

  public void setTotalAPagar(BigDecimal totalAPagar) {
    this.totalAPagar = totalAPagar;
  }

  public BigDecimal getMontoInsertado() {
    return montoInsertado;
  }

  public void setMontoInsertado(BigDecimal montoInsertado) {
    this.montoInsertado = montoInsertado;
  }

  public BigDecimal getCambio() {
    return cambio;
  }

  public void setCambio(BigDecimal cambio) {
    this.cambio = cambio;
  }

  public List<DenominacionCambio> getCambioDetallado() {
    return cambioDetallado;
  }

  public void setCambioDetallado(List<DenominacionCambio> cambioDetallado) {
    this.cambioDetallado = cambioDetallado;
  }

  public boolean isExitoso() {
    return exitoso;
  }

  public void setExitoso(boolean exitoso) {
    this.exitoso = exitoso;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
}
