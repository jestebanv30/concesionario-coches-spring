package com.project.coches.persistance.entity;

import com.project.coches.domain.dto.CarPurchaseRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad de compras
 */
@Getter
@Setter
@Entity
@Table(name = "compras")
public class PurchaseEntity {

    /**
     * Atributos de compras, id única autoincrementable
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_factura")
    private Integer numberBill;

    @Column(name = "cliente_cedula")
    private String cardIdCustomer;

    @Column(name = "fecha")
    private LocalDateTime date;

    private Double total;

    @Column(name = "medio_pago")
    private String paymentMethod;

    /**
     * Relación N:M con coches_compras
     */
    @OneToMany(mappedBy = "purchaseEntity", cascade = CascadeType.ALL)
    private List<CarPurchaseEntity> carsPurchase;

    /**
     * Relación M:N con cliente
     */
    @ManyToOne
    @JoinColumn(name = "cliente_cedula", insertable = false, updatable = false)
    private CustomerEntity customerEntity;

}
