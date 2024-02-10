package com.project.coches.domain.repository;

import com.project.coches.domain.pojo.CarBrandPojo;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de marca coche
 */
public interface ICarBrandRepository {

    /**
     * Obtiene lista de marcas coches pojo
     * @return lista de MarcasCochesPojo
     */
    List<CarBrandPojo> getAll();

    /**
     * Optional (en caso de encontrar devuelve la MarcaCoche y
     * en caso de que no, evita el null)
     * @param id Marca de coche a buscar
     * @return Marca de coche encontrada
     */
    Optional<CarBrandPojo> getCarBrand(Integer id);

    /**
     * Guarda una marca de coche
     * @param carBrandPojoNew MarcaCochePojo nueva
     * @return Guarda una MarcaCochePojo
     */
    CarBrandPojo save(CarBrandPojo carBrandPojoNew);

    /**
     * Elimina una marca de coche
     * @param idCarBrand Marca de coche a eliminar
     */
    void delete(Integer idCarBrand);

}
