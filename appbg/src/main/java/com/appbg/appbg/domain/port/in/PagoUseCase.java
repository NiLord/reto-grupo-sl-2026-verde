package com.appbg.appbg.domain.port.in;

import com.appbg.appbg.domain.model.Denominacion;
import com.appbg.appbg.domain.model.ResultadoPago;
import java.util.List;
import java.util.Map;

public interface PagoUseCase {
  List<Denominacion> obtenerDenominaciones();

  ResultadoPago procesarPago(Map<Long, Integer> denominacionesInsertadas);
}
