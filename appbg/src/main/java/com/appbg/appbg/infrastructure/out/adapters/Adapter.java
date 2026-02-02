package com.appbg.appbg.infrastructure.out.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.appbg.appbg.domain.model.Producto;
import com.appbg.appbg.domain.port.out.ProductoRepository;
import com.appbg.appbg.infrastructure.out.entity.ProductoEntity;
import com.appbg.appbg.infrastructure.out.repository.ProductoJpaRepository;

@Component
public class Adapter implements ProductoRepository {
  private final ProductoJpaRepository productoJpaRepository;

  public Adapter(ProductoJpaRepository productoJpaRepository) {
    this.productoJpaRepository = productoJpaRepository;
  }

  @Override
  public List<Producto> listarProductos() {
    return productoJpaRepository.findAll().stream()
        .map(this::toProducto)
        .toList();
  }

  @Override
  public Optional<Producto> buscarPorId(Long id) {
    return productoJpaRepository.findById(id)
        .map(this::toProducto);
  }

  private Producto toProducto(ProductoEntity entity) {
    return new Producto(
        entity.getId(),
        entity.getNombre(),
        entity.getPrecio(),
        entity.getCantidadDisponible());
  }
}
