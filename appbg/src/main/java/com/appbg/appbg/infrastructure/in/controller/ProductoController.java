package com.appbg.appbg.infrastructure.in.controller;

import com.appbg.appbg.domain.model.Producto;
import com.appbg.appbg.domain.port.in.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
  private final ProductUseCase productUseCase;

  public ProductoController(ProductUseCase productUseCase) {
    this.productUseCase = productUseCase;
  }

  @GetMapping
  public ResponseEntity<List<Producto>> getAllProductos() {
    List<Producto> productos = productUseCase.listarProductos();
    return ResponseEntity.ok(productos);
  }
}
