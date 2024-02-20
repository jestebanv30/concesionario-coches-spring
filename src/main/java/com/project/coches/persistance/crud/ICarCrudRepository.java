package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarCrudRepository extends JpaRepository<CarEntity, Integer> {

    List<CarEntity> findAllByCarBrandId(Integer id);

    List<CarEntity> findAllByPriceLessThanEqualOrderByPriceDesc(Double price);

    List<CarEntity> findAllByPriceGreaterThanEqualOrderByPriceAsc(Double price);

}
