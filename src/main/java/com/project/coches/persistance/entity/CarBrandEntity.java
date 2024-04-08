package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entidad de marca coche
 */
@Getter
@Setter
@Entity
@Table(name = "marca_coche")
public class CarBrandEntity {

    /**
     * Atributos de marca coche, en ID se genera
     * automático
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion")
    private String description;

    /**
     * Relación N:M con coches, orphanRemoval = true, significa que puedo eliminar
     * una marca de coche aunque tenga coches asociados a ella.
     */
    @OneToMany(mappedBy = "carBrandEntity", orphanRemoval = true)
    private List<CarEntity> carEntities;
}
