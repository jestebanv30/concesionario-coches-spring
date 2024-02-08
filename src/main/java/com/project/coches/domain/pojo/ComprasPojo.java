package com.project.coches.domain.pojo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Objeto plato de Compras
 */

@Getter @Setter
public class ComprasPojo {

    /**
     * Atributos de ComprasPojo
     */
    private Integer numero_factura;

    private Date fecha;

    private Integer total;

    private String medio_pago;
}
