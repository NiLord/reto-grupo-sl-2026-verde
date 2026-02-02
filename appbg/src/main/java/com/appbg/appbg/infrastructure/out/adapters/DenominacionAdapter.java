package com.appbg.appbg.infrastructure.out.adapters;

import com.appbg.appbg.domain.model.Denominacion;
import com.appbg.appbg.domain.model.TipoDenominacion;
import com.appbg.appbg.domain.port.out.DenominacionRepository;
import com.appbg.appbg.infrastructure.out.entity.DenominacionEntity;
import com.appbg.appbg.infrastructure.out.repository.DenominacionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DenominacionAdapter implements DenominacionRepository {

  private final DenominacionJpaRepository denominacionJpaRepository;

  public DenominacionAdapter(DenominacionJpaRepository denominacionJpaRepository) {
    this.denominacionJpaRepository = denominacionJpaRepository;
  }

  @Override
  public List<Denominacion> listarDenominaciones() {
    return denominacionJpaRepository.findAll().stream()
        .map(this::toDenominacion)
        .toList();
  }

  @Override
  public void actualizarCantidad(Long id, Integer cantidad) {
    DenominacionEntity entity = denominacionJpaRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Denominación no encontrada"));
    entity.setCantidadDisponible(cantidad);
    denominacionJpaRepository.save(entity);
  }

  private Denominacion toDenominacion(DenominacionEntity entity) {
    TipoDenominacion tipo = TipoDenominacion.valueOf(entity.getTipo());
    return new Denominacion(
        entity.getId(),
        entity.getValor(),
        tipo,
        entity.getCantidadDisponible());
  }
}
