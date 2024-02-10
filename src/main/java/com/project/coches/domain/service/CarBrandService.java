package com.project.coches.domain.service;

import com.project.coches.domain.pojo.CarBrandPojo;
import com.project.coches.domain.repository.ICarBrandRepository;
import com.project.coches.persistance.repository.CarBrandRepository;
import com.project.coches.persistance.repository.ICarBrandCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarBrandService implements ICarBrandService{

    private final ICarBrandRepository iCarBrandRepository;

    @Override
    public List<CarBrandPojo> getAll() {
        return iCarBrandRepository.getAll();
    }

    @Override
    public Optional<CarBrandPojo> getCarBrand(Integer id) {
        return iCarBrandRepository.getCarBrand(id);
    }

    @Override
    public CarBrandPojo save(CarBrandPojo carBrandPojoNew) {
        return iCarBrandRepository.save(carBrandPojoNew);
    }

    @Override
    public Optional<CarBrandPojo> update(CarBrandPojo carBrandPojo) {

        if (iCarBrandRepository.getCarBrand(carBrandPojo.getId()).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(iCarBrandRepository.save(carBrandPojo));
    }

    @Override
    public boolean delete(Integer idCarBrand) {
        try {
            iCarBrandRepository.delete(idCarBrand);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
