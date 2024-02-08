package com.project.coches.persistance.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "compras")
public class ComprasEntity {
    /**
     * Atributos de comprasEmtity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero_factura;

    private Date fecha;

    private Integer total;

    private String medio_pago;
}
