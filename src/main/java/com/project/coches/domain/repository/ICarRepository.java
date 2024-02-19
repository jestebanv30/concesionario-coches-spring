package com.project.coches.domain.repository;

import com.project.coches.domain.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface ICarRepository {

    List<CarDto> getAll();

    List<CarDto> getByIdCarBrand(Integer carBrandId);

    List<CarDto> getCarByPriceLessThan(Double price);

    List<CarDto> getCarByPriceGreaterThan(Double price);

    Optional<CarDto> getCarById(Integer idCar);

    CarDto save(CarDto newCarDto);

    void delete(Integer idCar);
}
