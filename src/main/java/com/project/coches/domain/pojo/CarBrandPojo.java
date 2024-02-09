package com.project.coches.domain.pojo;


import lombok.Getter;
import lombok.Setter;

/**
 * Pojo (DTO objeto plano en java) de marca coche
 */
@Getter @Setter
public class CarBrandPojo {

    /**
     * Atributos de marca coche
     */
    private Integer id;

    private String description;
}
