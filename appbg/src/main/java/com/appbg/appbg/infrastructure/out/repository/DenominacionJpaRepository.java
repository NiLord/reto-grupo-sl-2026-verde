package com.appbg.appbg.infrastructure.out.repository;

import com.appbg.appbg.infrastructure.out.entity.DenominacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenominacionJpaRepository extends JpaRepository<DenominacionEntity, Long> {
}
