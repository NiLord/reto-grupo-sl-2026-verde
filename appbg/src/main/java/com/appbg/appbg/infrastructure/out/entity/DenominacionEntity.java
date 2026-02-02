package com.appbg.appbg.infrastructure.out.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "denominacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenominacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(name = "cantidad_disponible", nullable = false)
    private Integer cantidadDisponible = 0;
}
