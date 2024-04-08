package com.project.coches.domain.useCase;

import com.project.coches.domain.dto.CarDto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de caso de uso para coche
 */
public interface ICarUseCase {
    /**
     * Obtiene una lista de coche
     * @return lista de coche
     */
    List<CarDto> getAll();

    /**
     * Filtra(lista) todos los coches asociados a una Id de marca coche
     * @param carBrandId id de marca coche a buscar
     * @return Lista de coches que pertenezcan a carBrandId
     */
    List<CarDto> getByIdCarBrand(Integer carBrandId);

    /**
     * Filtra(lista) todos los coches menor o igual al precio dado
     * en orden de mayor a menor
     * @param price precio dado para el filtro
     * @return lista de coches con el precio menor o igual a price
     */
    List<CarDto> getCarByPriceLessThan(Double price);

    /**
     * Filtra(lista) todos los coches mayor o igual al precio dado
     * en orden de menor a mayor
     * @param price precio dado para el filtro
     * @return lista de coches con el precio mayor o igual a price
     */
    List<CarDto> getCarByPriceGreaterThan(Double price);

    /**
     * Busca un coche dada su id
     * @param idCar id a buscar
     * @return coche encontrado
     */
    Optional<CarDto> getCarById(Integer idCar);

    /**
     * Guarda un coche
     * @param newCarDto coche nuevo
     * @return coche guardado
     */
    CarDto save(CarDto newCarDto);

    /**
     * Actualiza un coche
     * @param carDtoNew coche a actualizar
     * @return coche actualizado
     */
    Optional<CarDto> update(CarDto carDtoNew);

    /**
     * Elimina un coche
     * @param idCar coche a eliminar
     * @return coche eliminado
     */
    boolean delete(Integer idCar);

}
