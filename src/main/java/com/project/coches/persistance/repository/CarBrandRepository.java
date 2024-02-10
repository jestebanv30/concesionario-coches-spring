package com.project.coches.persistance.repository;

import com.project.coches.domain.pojo.CarBrandPojo;
import com.project.coches.domain.repository.ICarBrandRepository;
import com.project.coches.persistance.entity.CarBrandEntity;
import com.project.coches.persistance.mapper.ICarBrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CarBrandRepository implements ICarBrandRepository {

    private final ICarBrandCrudRepository iCarBrandCrudRepository;

    private final ICarBrandMapper iCarBrandMapper;

    /**
     * Lista de MarcasCochesPojo a través de listar MarcasCochesEntity
     * @return Lista de MarcasCochesPojo
     */
    @Override
    public List<CarBrandPojo> getAll() {
        return iCarBrandMapper.toMarcasCochePojo(iCarBrandCrudRepository.findAll());
    }

    @Override
    public Optional<CarBrandPojo> getCarBrand(Integer id) {
        try {

        } catch (Exception e){

        }
    }

    /**
     * Guardar una MarcaCochePojo y para la BD una MarcaCocheEntity
     * @param carBrandPojoNew MarcaCochePojo nueva
     * @return MarcaCochePojo guardada
     */
    @Override
    public CarBrandPojo save(CarBrandPojo carBrandPojoNew) {
        CarBrandEntity carBrandEntity = iCarBrandMapper.toMarcaCocheEntity(carBrandPojoNew);
        return iCarBrandMapper.toMarcaCochePojo(iCarBrandCrudRepository.save(carBrandEntity));
    }

    @Override
    public void delete(Integer idCarBrand) {

    }
}
