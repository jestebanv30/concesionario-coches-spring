package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entidad de coche
 */
@Getter
@Setter
@Entity
@Table(name = "coches")
public class CarEntity {

    /**
     * Atributos de coche, en ID se genera
     * automático
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_coche")
    private Integer codeCar;

    @Column(name = "marca_coche_id")
    private Integer carBrandId;

    @Column(name = "referencia_marca")
    private String reference;

    @Column(name = "precio")
    private Double price;

    @Column(name = "anio_modelo")
    private Double modelYear;

    private String color;

    @Column(name = "numeros_caballos_fuerza")
    private Double horsepower;

    @Column(name = "cantidad_puertas")
    private Integer numberDoor;

    @Column(name = "cilindraje")
    private Double engineDisplacement;

    @Column(name = "transimision")
    private String transmission;

    @Column(name = "tipo_combustible")
    private String fuelType;

    @Column(name = "cantidad_asientos")
    private Integer numberSeat;

    @Column(name = "traccion")
    private String traction;

    @Column(name = "direccion")
    private String steering;

    @Column(name = "categoria")
    private String category;

    @Column(name = "ruta_imagen")
    private String imagePath;

    private Integer stock;

    /**
     * Relación M:N con marca coche (muchos coches pueden estar asociados a una marca)
     * no se inserta ni se actualiza, solo se referencia la marca existente
     */
    @ManyToOne
    @JoinColumn(name = "marca_coche_id", insertable=false, updatable=false)
    private CarBrandEntity carBrandEntity;

    /**
     * Relación N:M con coches_compras, un coche puede estar en muchas compras (Stock)
     */
    @OneToMany(mappedBy = "carEntity")
    private List<CarPurchaseEntity> carPurchaseEntities;
}
