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

    /**
     * variables de instancia de la interfaz ICarBrandCrudRepository (BD)
     * y ICarBrandMapper (Mapeador)
     */
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

    /**
     * Busca una marca coche Entity y la transformo en Pojo
     * @param id Marca de coche a buscar
     * @return una MarcaCochePojo encontrada
     */
    @Override
    public Optional<CarBrandPojo> getCarBrand(Integer id) {
        return iCarBrandCrudRepository.findById(id).
                map(carBrandEntity -> iCarBrandMapper.toMarcaCochePojo(carBrandEntity));
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
        iCarBrandCrudRepository.deleteById(idCarBrand);
    }
}
