package com.project.coches.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de cliente para responder con la contraseña
 */
@Getter
@Setter
@AllArgsConstructor
public class ResponseCustomerDto {

    /**
     * Atributo de ResponseCustomer
     */

    private String password;
}
