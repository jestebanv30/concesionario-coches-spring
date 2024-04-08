package com.project.coches.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para responder compra
 */
@Getter
@Setter
public class PurchaseResponseDto {

    /**
     * Atributos de compra response
     */

    private Integer numberBill;

    private String cardIdCustomer;

    private LocalDateTime date;

    private Double total;

    private String paymentMethod;

    private List<CarPurchaseResponseDto> carsPurchase;

}
