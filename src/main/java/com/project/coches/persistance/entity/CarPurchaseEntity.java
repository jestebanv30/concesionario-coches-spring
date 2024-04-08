package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad de coches_compras (referencia a la factura)
 */
@Getter
@Setter
@Entity
@Table(name = "coches_compras")
public class CarPurchaseEntity {

    /**
     * Atributos de coches_compras
     * @EmbeddedId representa a una clave primaria compuesta
     */
    @EmbeddedId
    private CarPurchasePk id;

    @Column(name = "cantidad")
    private Integer quantity;

    private Integer total;

    /**
     * Relación M:N con compras, hace referencia a que muchos coches pueden estar en una compra
     */
    @ManyToOne
    @MapsId(value = "purchaseNumberBill")
    @JoinColumn(name = "compras_numero_factura", insertable = false, updatable = false)
    private PurchaseEntity purchaseEntity;

    /**
     * Relación M:N con coches
     */
    @ManyToOne
    @JoinColumn(name = "coches_codigo_coche", insertable = false, updatable = false)
    private CarEntity carEntity;
}
