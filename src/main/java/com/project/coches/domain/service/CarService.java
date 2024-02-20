package com.project.coches.domain.service;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.domain.useCase.ICarUseCase;
import com.project.coches.exception.typesexceptions.NoExistCarException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarService implements ICarUseCase {

    private final ICarRepository iCarRepository;

    @Override
    public List<CarDto> getAll() {
        return iCarRepository.getAll();
    }

    @Override
    public List<CarDto> getByIdCarBrand(Integer carBrandId) {
        return iCarRepository.getByIdCarBrand(carBrandId);
    }

    @Override
    public List<CarDto> getCarByPriceLessThan(Double price) {
        return iCarRepository.getCarByPriceLessThan(price);
    }

    @Override
    public List<CarDto> getCarByPriceGreaterThan(Double price) {
        return iCarRepository.getCarByPriceGreaterThan(price);
    }

    @Override
    public Optional<CarDto> getCarById(Integer idCar) {

        Optional<CarDto> existingCarById = iCarRepository.getCarById(idCar);

        if (existingCarById.isEmpty()) {
            throw new NoExistCarException();
        }

        return iCarRepository.getCarById(idCar);
    }

    @Override
    public CarDto save(CarDto newCarDto) {
        return iCarRepository.save(newCarDto);
    }

    @Override
    public Optional<CarDto> update(CarDto carDtoNew) {

        Optional<CarDto> existingCar = iCarRepository.getCarById(carDtoNew.getCode_car());

        if (existingCar.isEmpty()) {
            throw new NoExistCarException();
        }

        return Optional.of(iCarRepository.save(carDtoNew));
    }

    @Override
    public boolean delete(Integer idCar) {

        Optional<CarDto> existingCarById = iCarRepository.getCarById(idCar);

        if (existingCarById.isEmpty()) {
            throw new NoExistCarException();
        }

        iCarRepository.delete(idCar);
        return true;
    }
}
