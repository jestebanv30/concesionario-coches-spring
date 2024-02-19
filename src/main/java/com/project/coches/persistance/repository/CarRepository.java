package com.project.coches.persistance.repository;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.persistance.crud.ICarCrudRepository;
import com.project.coches.persistance.mapper.ICarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CarRepository implements ICarRepository {

    private final ICarCrudRepository iCarCrudRepository;

    private final ICarMapper iCarMapper;


    @Override
    public List<CarDto> getAll() {
        return iCarMapper.toCarsDto(iCarCrudRepository.findAll());
    }

    @Override
    public List<CarDto> getByIdCarBrand(Integer carBrandId) {
        return iCarMapper.toCarsDto(iCarCrudRepository.findAllByCarBrandId(carBrandId));
    }

    @Override
    public List<CarDto> getCarByPriceLessThan(Double price) {
        return iCarMapper.toCarsDto(iCarCrudRepository.findAllByPriceLessThanEqualOrderByPriceDesc(price));
    }

    @Override
    public List<CarDto> getCarByPriceGreaterThan(Double price) {
        return iCarMapper.toCarsDto(iCarCrudRepository.findAllByPriceGreaterThanEqualOrderByPriceAsc(price));
    }

    @Override
    public Optional<CarDto> getCarById(Integer idCar) {
        return iCarCrudRepository.findById(idCar).map(iCarMapper::toCarDto);
    }

    @Override
    public CarDto save(CarDto newCarDto) {
        return iCarMapper.toCarDto(iCarCrudRepository.save(iCarMapper.toCarEntity(newCarDto)));
    }

    @Override
    public void delete(Integer idCar) {
        iCarCrudRepository.deleteById(idCar);
    }
}
