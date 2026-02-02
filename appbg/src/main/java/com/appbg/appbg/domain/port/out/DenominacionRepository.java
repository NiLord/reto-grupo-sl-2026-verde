package com.appbg.appbg.domain.port.out;

import com.appbg.appbg.domain.model.Denominacion;
import java.util.List;

public interface DenominacionRepository {
  List<Denominacion> listarDenominaciones();

  void actualizarCantidad(Long id, Integer cantidad);
}
