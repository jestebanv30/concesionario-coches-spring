package com.project.coches.domain.service;

import com.project.coches.domain.pojo.CarBrandPojo;

import java.util.List;
import java.util.Optional;

public interface ICarBrandService {
    /**
     * Obtiene lista de marcas coches pojo
     *
     * @return lista de MarcasCochesPojo
     */
    List<CarBrandPojo> getAll();

    /**
     * Optional (en caso de encontrar devuelve la MarcaCoche y
     * en caso de que no, evita el null)
     *
     * @param id Marca de coche a buscar
     * @return Marca de coche encontrada
     */
    Optional<CarBrandPojo> getCarBrand(Integer id);

    /**
     * Busca una descripción existente de marca coche
     *
     * @param description descripcion a buscar
     * @return descripcion encontrada
     */
    Optional<CarBrandPojo> getCarBrandByDescription(String description);

    /**
     * Guarda una marca de coche
     *
     * @param carBrandPojoNew MarcaCochePojo nueva
     * @return Guarda una MarcaCochePojo
     */
    CarBrandPojo save(CarBrandPojo carBrandPojoNew);

    Optional<CarBrandPojo> update(CarBrandPojo carBrandPojo);

    /**
     * Elimina una marca de coche
     *
     * @param idCarBrand Marca de coche a eliminar
     */
    boolean delete(Integer idCarBrand);
}
