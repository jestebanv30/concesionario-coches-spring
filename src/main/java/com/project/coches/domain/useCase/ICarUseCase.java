package com.project.coches.domain.useCase;

import com.project.coches.domain.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface ICarUseCase {

    List<CarDto> getAll();

    List<CarDto> getByIdCarBrand(Integer carBrandId);

    List<CarDto> getCarByPriceLessThan(Double price);

    List<CarDto> getCarByPriceGreaterThan(Double price);

    Optional<CarDto> getCarById(Integer idCar);

    CarDto save(CarDto newCarDto);

    Optional<CarDto> update(CarDto carDtoNew);

    boolean delete(Integer idCar);

}
