package com.project.coches.domain.service;

import com.project.coches.domain.dto.CarBrandDto;
import com.project.coches.domain.repository.ICarBrandRepository;
import com.project.coches.domain.useCase.ICarBrandUseCase;
import com.project.coches.exception.typesexceptions.ExistingCarBrandValidationException;
import com.project.coches.exception.typesexceptions.ValidationOfNonExistentCarBrand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de marca coche
 */
@RequiredArgsConstructor
@Service
public class CarBrandService implements ICarBrandUseCase {

    private final ICarBrandRepository iCarBrandRepository;

    @Override
    public List<CarBrandDto> getAll() {
        return iCarBrandRepository.getAll();
    }

    @Override
    public Optional<CarBrandDto> getCarBrand(Integer id) {

        if (iCarBrandRepository.getCarBrand(id).isEmpty()){
            throw new ValidationOfNonExistentCarBrand();
        }

        return iCarBrandRepository.getCarBrand(id);
    }

    @Override
    public Optional<CarBrandDto> getCarBrandByDescription(String description) {

        if (iCarBrandRepository.getCarBrandByDescription(description).isEmpty()){
            throw new ValidationOfNonExistentCarBrand();
        }

        return iCarBrandRepository.getCarBrandByDescription(description);
    }

    @Override
    public CarBrandDto save(CarBrandDto carBrandDtoNew) {

        if (iCarBrandRepository.getCarBrandByDescription(carBrandDtoNew.getDescription()).isPresent()) {
            throw new ExistingCarBrandValidationException();
        }
        return iCarBrandRepository.save(carBrandDtoNew);
    }

    @Override
    public Optional<CarBrandDto> update(CarBrandDto carBrandDto) {

        if (iCarBrandRepository.getCarBrand(carBrandDto.getId()).isEmpty()) {
            throw new ValidationOfNonExistentCarBrand();
        } else if (iCarBrandRepository.getCarBrandByDescription(carBrandDto.getDescription()).isPresent()) {
            throw new ExistingCarBrandValidationException();
        } else {
            return Optional.of(iCarBrandRepository.save(carBrandDto));
        }
    }

    @Override
    public boolean delete(Integer idCarBrand) {

        if (iCarBrandRepository.getCarBrand(idCarBrand).isEmpty()) {
            throw new ValidationOfNonExistentCarBrand();
        }

        iCarBrandRepository.delete(idCarBrand);
        return true;

    }
}
