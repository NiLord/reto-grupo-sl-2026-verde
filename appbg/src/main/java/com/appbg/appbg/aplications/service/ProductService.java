package com.appbg.appbg.aplications.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.appbg.appbg.domain.model.Producto;
import com.appbg.appbg.domain.port.in.ProductUseCase;
import com.appbg.appbg.domain.port.out.ProductoRepository;
@Service
public class ProductService implements ProductUseCase{
  private final ProductoRepository productoRepository;
  

  public ProductService(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }


  @Override
  public List<Producto> listarProductos() {
    List<Producto> listaProductos = productoRepository.listarProductos();
    return listaProductos;
    
  }

}
