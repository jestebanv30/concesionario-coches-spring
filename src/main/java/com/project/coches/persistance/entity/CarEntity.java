package com.project.coches.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "coches")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_coche")
    private Integer code_car;

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
    private Integer traction;

    @Column(name = "direccion")
    private String steering;

    @Column(name = "categoria")
    private String category;

    @Column(name = "ruta_imagen")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "marca_coche_id", insertable=false, updatable=false)
    private CarBrandEntity carBrandEntity;

    @OneToMany(mappedBy = "carEntity")
    private List<CarPurchaseEntity> carPurchaseEntities;
}
