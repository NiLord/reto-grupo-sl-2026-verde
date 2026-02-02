package com.appbg.appbg.infrastructure.in.dto;

import com.appbg.appbg.domain.model.ItemCarrito;

import java.math.BigDecimal;
import java.util.List;

public class CarritoResponse {
  private List<ItemCarrito> items;
  private BigDecimal total;
  private Integer totalItems;

  public CarritoResponse() {
  }

  public CarritoResponse(List<ItemCarrito> items, BigDecimal total, Integer totalItems) {
    this.items = items;
    this.total = total;
    this.totalItems = totalItems;
  }

  public List<ItemCarrito> getItems() {
    return items;
  }

  public void setItems(List<ItemCarrito> items) {
    this.items = items;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Integer getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(Integer totalItems) {
    this.totalItems = totalItems;
  }
}
