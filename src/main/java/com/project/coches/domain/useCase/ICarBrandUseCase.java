package com.project.coches.domain.useCase;

import com.project.coches.domain.dto.CarBrandDto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de caso de uso para marca coche
 */
public interface ICarBrandUseCase {
    /**
     * Obtiene lista de marcas coches
     * @return lista de marca coche
     */
    List<CarBrandDto> getAll();

    /**
     * Buscar una marca de coche por ID
     * @param id Marca de coche a buscar
     * @return Marca de coche encontrada
     */
    Optional<CarBrandDto> getCarBrand(Integer id);

    /**
     * Busca una descripción existente de marca coche
     * @param description descripcion a buscar
     * @return descripcion encontrada
     */
    Optional<CarBrandDto> getCarBrandByDescription(String description);

    /**
     * Guarda una marca de coche
     * @param carBrandDtoNew MarcaCochePojo nueva
     * @return Guarda una MarcaCochePojo
     */
    CarBrandDto save(CarBrandDto carBrandDtoNew);

    /**
     * Actualiza una marca coche
     * @param carBrandDto marca coche a actualizar
     * @return marca coche actualizada
     */
    Optional<CarBrandDto> update(CarBrandDto carBrandDto);

    /**
     * Elimina una marca coche
     * @param idCarBrand Marca de coche a eliminar
     */
    boolean delete(Integer idCarBrand);
}
