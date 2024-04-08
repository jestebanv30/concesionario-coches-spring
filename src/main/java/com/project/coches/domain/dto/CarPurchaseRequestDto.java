package com.project.coches.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para recibir coches compras
 */
@Getter
@Setter
public class CarPurchaseRequestDto {

    /**
     * Atributos de coches compras
     */

    private Integer purchaseNumberBill;

    private Integer codeCar;

    private Integer quantity;

    private Integer total;

}
