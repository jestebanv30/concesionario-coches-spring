package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de marca coche (directamente de bd)
 */
@Getter @Setter
@Entity
@Table(name = "marca_coche")
public class CarBrandEntity {

    /**
     * Atributos de marca coche
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion")
    private String description;

    @OneToMany(mappedBy = "carBrandEntity", orphanRemoval = true)
    private List<CarEntity> carEntities;

}
