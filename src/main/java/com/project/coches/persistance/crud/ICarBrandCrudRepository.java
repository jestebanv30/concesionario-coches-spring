package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.CarBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interfaz de crud en la base de datos para marca coche
 */
public interface ICarBrandCrudRepository extends JpaRepository<CarBrandEntity, Integer> {

    /**
     * findByDescriptionIgnoreCase, Spring Data JPA interpreta ese nombre y genera automáticamente
     * una consulta SQL correspondiente para buscar en la base de datos.
     * Busca una marca coche dada su descripción ignorando mayúsculas y minúsculas
     * @param description descripcion a buscar
     * @return marca coche con descripcion encontrada
     */
    Optional<CarBrandEntity> findByDescriptionIgnoreCase(String description);
}
