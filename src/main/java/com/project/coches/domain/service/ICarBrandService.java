package com.project.coches.domain.service;

import com.project.coches.domain.pojo.CarBrandPojo;

import java.util.List;
import java.util.Optional;

public interface ICarBrandService {

    /**
     * Devuelve una lista con todas las marcas de coche
     * @return lista con marcas de coches
     */
    List<CarBrandPojo> getAll();

    /**
     * Consulta una marca de coche dada su id
     * @param id parametro id a constultar
     * @return Devuelve una marca de coche
     */
    Optional<CarBrandPojo> getCarBrand(Integer id);

    /**
     * Guarda una nueva marca coche
     * @param newCarBrand marca coche a guardar
     * @return marca coche guardada
     */
    CarBrandPojo save(CarBrandPojo newCarBrand);

    /**
     * Elimina una marca coche dada su ID
     * @param idCarBrand marca coche a eliminar
     */
    boolean delete(Integer idCarBrand);
}
