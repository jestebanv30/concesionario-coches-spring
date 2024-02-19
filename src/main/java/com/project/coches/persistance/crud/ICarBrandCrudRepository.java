package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.CarBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarBrandCrudRepository extends JpaRepository<CarBrandEntity, Integer> {
}
