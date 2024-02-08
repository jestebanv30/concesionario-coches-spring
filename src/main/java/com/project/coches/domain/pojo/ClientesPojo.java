package com.project.coches.domain.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto plano de Clientes
 */

@Getter @Setter
public class ClientesPojo {
    /**
     * Atributos de clientesPojo
     */
    private String cedula;

    private String name;

    private String email;

    private Integer phone;

    private Integer active;

    private String qualification_datacredit;
}
