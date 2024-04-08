package com.project.coches.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO de coche
 */
@Getter
@Setter
public class CarDto {

    /**
     * Atributos de cocheDto
     */

    private Integer codeCar;

    private Integer carBrandId;

    private String carBrandDescription;

    private String reference;

    private Double price;

    private Double modelYear;

    private String color;

    private Double horsepower;

    private Integer numberDoor;

    private Double engineDisplacement;

    private String transmission;

    private String fuelType;

    private Integer numberSeat;

    private String traction;

    private String steering;

    private String category;

    private String imagePath;

    private Integer stock;

}
