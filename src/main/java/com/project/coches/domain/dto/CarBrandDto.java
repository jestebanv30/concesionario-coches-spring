package com.project.coches.domain.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * Pojo (DTO objeto plano en java) de marca coche
 */
@Getter @Setter
public class CarBrandDto {

    /**
     * Atributos de marca coche
     */
    private Integer id;
    private String description;
}
