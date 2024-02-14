package com.project.coches.persistance.repository;

import com.project.coches.persistance.entity.CarBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICarBrandCrudRepository extends JpaRepository<CarBrandEntity, Integer> {

    /**
     * findByDescriptionIgnoreCase, Spring Data JPA interpreta ese nombre y genera automáticamente
     * una consulta SQL correspondiente para buscar en la base de datos.
     * En este caso, findByDescriptionIgnoreCase se traduce en una consulta que
     * busca una entidad de tipo CarBrandEntity donde el atributo description
     * coincide con el valor proporcionado (ignorando mayúsculas y minúsculas).
     *
     * @param description descripcion a buscar
     * @return descripcion encontrada
     */
    Optional<CarBrandEntity> findByDescriptionIgnoreCase(String description);
}
