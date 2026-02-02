package com.appbg.appbg.infrastructure.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appbg.appbg.infrastructure.out.entity.ProductoEntity;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {

}
