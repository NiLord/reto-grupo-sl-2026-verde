package com.appbg.appbg.infrastructure.in.dto;

import java.math.BigDecimal;
import java.util.Map;

public class PagoRequest {
  private Map<Long, Integer> denominacionesInsertadas; // denominacionId -> cantidad

  public PagoRequest() {
  }

  public PagoRequest(Map<Long, Integer> denominacionesInsertadas) {
    this.denominacionesInsertadas = denominacionesInsertadas;
  }

  public Map<Long, Integer> getDenominacionesInsertadas() {
    return denominacionesInsertadas;
  }

  public void setDenominacionesInsertadas(Map<Long, Integer> denominacionesInsertadas) {
    this.denominacionesInsertadas = denominacionesInsertadas;
  }
}
