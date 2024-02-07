package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad de marca coche (directamente de bd)
 */
@Getter @Setter
@Entity
@Table(name = "marca_coche")
public class MarcaCocheEntity {

    /**
     * Atributos de marca coche
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion")
    private String description;
}
