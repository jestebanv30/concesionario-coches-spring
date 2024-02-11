package com.project.coches.domain.service;

import com.project.coches.domain.pojo.CarBrandPojo;
import com.project.coches.domain.repository.ICarBrandRepository;
import com.project.coches.persistance.repository.CarBrandRepository;
import com.project.coches.persistance.repository.ICarBrandCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
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
    public Optional<CarBrandPojo> getCarBrandByDescription(String description) {
        return iCarBrandRepository.getCarBrandByDescription(description);
    }

    @Override
    public CarBrandPojo save(CarBrandPojo carBrandPojoNew) {

        Optional<CarBrandPojo> existingCarBrand = getCarBrandByDescription(carBrandPojoNew.getDescription());

        if (existingCarBrand.isPresent()) {
            return null;
        } else {
            return iCarBrandRepository.save(carBrandPojoNew);
        }
    }

    @Override
    public Optional<CarBrandPojo> update(CarBrandPojo carBrandPojo) {

        Optional<CarBrandPojo> existingDescription = getCarBrandByDescription(carBrandPojo.getDescription());

        Optional<CarBrandPojo> existingID = getCarBrand(carBrandPojo.getId());

        if (existingID.isEmpty()) {
            return Optional.empty();
        } else if (existingDescription.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(iCarBrandRepository.save(carBrandPojo));
        }
    }

    @Override
    public boolean delete(Integer idCarBrand) {

        if (iCarBrandRepository.getCarBrand(idCarBrand).isEmpty()){
            return false;
        }

        iCarBrandRepository.delete(idCarBrand);
        return true;

    }
}
