package com.project.coches.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "clientes")
public class ClientesEntity {

    /**
     * Atributos de Clientes
     */
    @Id
    private String cedula;

    @Column(name = "nombre_completo")
    private String name;

    @Column(name = "correo")
    private String email;

    @Column(name = "numero_celular")
    private Integer phone;

    @Column(name = "activo")
    private Boolean active;

    @Column(name = "calificacion_datacredito")
    private String qualification_datacredit;

}
