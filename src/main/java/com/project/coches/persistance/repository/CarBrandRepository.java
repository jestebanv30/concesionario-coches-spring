package com.project.coches.persistance.repository;

import com.project.coches.domain.dto.CarBrandDto;
import com.project.coches.domain.repository.ICarBrandRepository;
import com.project.coches.persistance.crud.ICarBrandCrudRepository;
import com.project.coches.persistance.entity.CarBrandEntity;
import com.project.coches.persistance.mapper.ICarBrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de marca coche
 */
@RequiredArgsConstructor
@Repository
public class CarBrandRepository implements ICarBrandRepository {

    private final ICarBrandCrudRepository iCarBrandCrudRepository;

    private final ICarBrandMapper iCarBrandMapper;

    /**
     * Lista de MarcasCochesDto a través de listar MarcasCochesEntity
     * @return Lista de MarcasCochesDto
     */
    @Override
    public List<CarBrandDto> getAll() {
        return iCarBrandMapper.toMarcasCocheDto(iCarBrandCrudRepository.findAll());
    }

    /**
     * Busca una marca coche Entity y la transformo en Dto
     * @param id id de MarcaCoche a buscar
     * @return una MarcaCocheDto encontrada
     */
    @Override
    public Optional<CarBrandDto> getCarBrand(Integer id) {
        return iCarBrandCrudRepository.findById(id).
                map(iCarBrandMapper::toMarcaCocheDto);
    }

    /**
     * Busca una descripción existente de marca coche
     * @param description descripcion a buscar
     * @return descripcion encontrada
     */
    @Override
    public Optional<CarBrandDto> getCarBrandByDescription(String description) {
        return iCarBrandCrudRepository.findByDescriptionIgnoreCase(description)
                .map(iCarBrandMapper::toMarcaCocheDto);
    }

    /**
     * Guardar una MarcaCocheDto y para la BD una MarcaCocheEntity
     * @param carBrandDtoNew MarcaCocheDto nueva
     * @return MarcaCocheDto guardada
     */
    @Override
    public CarBrandDto save(CarBrandDto carBrandDtoNew) {
        CarBrandEntity carBrandEntity = iCarBrandMapper.toMarcaCocheEntity(carBrandDtoNew);
        return iCarBrandMapper.toMarcaCocheDto(iCarBrandCrudRepository.save(carBrandEntity));
    }

    /**
     * ELimmina una marca de coche en la bd
     * @param idCarBrand id de MarcaCoche a eliminar
     */
    @Override
    public void delete(Integer idCarBrand) {
        iCarBrandCrudRepository.deleteById(idCarBrand);
    }
}
