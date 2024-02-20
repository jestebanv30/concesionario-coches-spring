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

@RequiredArgsConstructor
@Repository
public class CarBrandRepository implements ICarBrandRepository {

    /**
     * variables de instancia de la interfaz ICarBrandCrudRepository (BD)
     * y ICarBrandMapper (Mapeador)
     */
    private final ICarBrandCrudRepository iCarBrandCrudRepository;

    private final ICarBrandMapper iCarBrandMapper;

    /**
     * Lista de MarcasCochesPojo a través de listar MarcasCochesEntity
     *
     * @return Lista de MarcasCochesPojo
     */
    @Override
    public List<CarBrandDto> getAll() {
        return iCarBrandMapper.toMarcasCocheDto(iCarBrandCrudRepository.findAll());
    }

    /**
     * Busca una marca coche Entity y la transformo en Pojo
     *
     * @param id Marca de coche a buscar
     * @return una MarcaCochePojo encontrada
     */
    @Override
    public Optional<CarBrandDto> getCarBrand(Integer id) {
        return iCarBrandCrudRepository.findById(id).
                map(iCarBrandMapper::toMarcaCocheDto);
    }

    /**
     * Busca una descripción existente de marca coche
     *
     * @param description descripcion a buscar
     * @return descripcion encontrada
     */
    @Override
    public Optional<CarBrandDto> getCarBrandByDescription(String description) {
        return iCarBrandCrudRepository.findByDescriptionIgnoreCase(description)
                .map(iCarBrandMapper::toMarcaCocheDto);
    }

    /**
     * Guardar una MarcaCochePojo y para la BD una MarcaCocheEntity
     *
     * @param carBrandDtoNew MarcaCochePojo nueva
     * @return MarcaCochePojo guardada
     */
    @Override
    public CarBrandDto save(CarBrandDto carBrandDtoNew) {
        CarBrandEntity carBrandEntity = iCarBrandMapper.toMarcaCocheEntity(carBrandDtoNew);
        return iCarBrandMapper.toMarcaCocheDto(iCarBrandCrudRepository.save(carBrandEntity));
    }

    @Override
    public void delete(Integer idCarBrand) {
        iCarBrandCrudRepository.deleteById(idCarBrand);
    }
}
