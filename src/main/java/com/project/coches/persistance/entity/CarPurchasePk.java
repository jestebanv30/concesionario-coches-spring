package com.project.coches.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase embebida que representa la clave primaria compuesta de coches_compras
 */
@Getter
@Setter
@Embeddable
public class CarPurchasePk implements Serializable {

    @Column(name = "compras_numero_factura")
    private Integer purchaseNumberBill;

    @Column(name = "coches_codigo_coche")
    private Integer codeCar;

}
