package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz de crud en la base de datos para coche
 */
public interface ICarCrudRepository extends JpaRepository<CarEntity, Integer> {

    /**
     * Lista(filtro) por id de marca coche que esté asociado a un coche
     * @param id id de marca coche a buscar
     * @return lista de coches que pertenezcan a la id de marca coche
     */
    List<CarEntity> findAllByCarBrandId(Integer id);

    /**
     * Filtra(lista) todos los coches menor o igual al precio dado
     * en orden de mayor a menor
     * @param price precio dado para el filtro
     * @return lista de coches con el precio menor o igual a price
     */
    List<CarEntity> findAllByPriceLessThanEqualOrderByPriceDesc(Double price);

    /**
     * Filtra(lista) todos los coches mayor o igual al precio dado
     * en orden de menor a mayor
     * @param price precio dado para el filtro
     * @return lista de coches con el precio mayor o igual a price
     */
    List<CarEntity> findAllByPriceGreaterThanEqualOrderByPriceAsc(Double price);

}
