package com.project.coches.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para recibir Compra
 */
@Getter
@Setter
public class PurchaseRequestDto {

    /**
     * Atributos de compra
     */

    private Integer numberBill;

    private String cardIdCustomer;

    private LocalDateTime date;

    private Double total;

    private String paymentMethod;

    private List<CarPurchaseRequestDto> carsPurchase;

}
