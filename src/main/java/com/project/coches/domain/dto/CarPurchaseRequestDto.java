package com.project.coches.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto del guardado de coches_compras
 */
@Getter
@Setter
public class CarPurchaseRequestDto {

    private Integer purchaseNumberBill;

    private Integer codeCar;

    private Integer quantity;

    private Integer total;

}
