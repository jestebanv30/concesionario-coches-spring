package com.project.coches.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para responder cochesCompras
 */
@Getter
@Setter
@AllArgsConstructor
public class CarPurchaseResponseDto {

    /**
     * Atributos de coches compras Response
     */

    private String referenceCar;

    private Integer quantity;

    private Integer total;

}
