package com.appbg.appbg.infrastructure.in.controller;

import com.appbg.appbg.domain.model.*;
import com.appbg.appbg.domain.model.Denominacion;
import com.appbg.appbg.domain.model.ResultadoPago;
import com.appbg.appbg.domain.port.in.PagoUseCase;
import com.appbg.appbg.infrastructure.in.dto.PagoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pago")
public class PagoController {

  private final PagoUseCase pagoUseCase;

  public PagoController(PagoUseCase pagoUseCase) {
    this.pagoUseCase = pagoUseCase;
  }

  @GetMapping("/denominaciones")
  public ResponseEntity<List<Denominacion>> obtenerDenominaciones() {
    List<Denominacion> denominaciones = pagoUseCase.obtenerDenominaciones();
    return ResponseEntity.ok(denominaciones);
  }

  @PostMapping("/procesar")
  public ResponseEntity<ResultadoPago> procesarPago(@RequestBody PagoRequest request) {
    ResultadoPago resultado = pagoUseCase.procesarPago(request.getDenominacionesInsertadas());
    return ResponseEntity.ok(resultado);
  }
}
