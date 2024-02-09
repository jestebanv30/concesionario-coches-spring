package com.project.coches.persistance.repository;

import com.project.coches.domain.pojo.CarBrandPojo;
import com.project.coches.domain.repository.ICarBrandRepository;
import com.project.coches.persistance.entity.CarBrandEntity;
import com.project.coches.persistance.mapper.ICarBrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de marca coche
 */
@RequiredArgsConstructor //Crea constructor con los atributos final
@Repository
public class CarBrandRepository implements ICarBrandRepository {


    /**
     * Crud de marca coche
     */
    private final ICarBrandCrudRepository iCardCarBrandCrudRepository;

    /**
     * Mapper de marca coche
     */
    private final ICarBrandMapper iCardBrandMapper;

    /**
     * La consulta con la bd me trae entity pero yo necesito pojo por eso para por el mapper
     * @return lista de marcacochepojos
     */
    @Override
    public List<CarBrandPojo> getAll() {
        return iCardBrandMapper.toMarcasCochePojo(iCardCarBrandCrudRepository.findAll());
    }

    /**
     * Optional sirve para evitar nulos, se ejecuta si existe un valor en el optional y
     * si no existe, no se estallaría el programa, mostraía un mensaje.
     * como busca by id y me traerá un entity y lo que necesito es un pojo, lo que hago
     * es que mediante la funcion .map lo convierto en pojo
     * @param id parametro id a constultar
     * @return un CarBrandPojo
     */
    @Override
    public Optional<CarBrandPojo> getCarBrand(Integer id) {
        return iCardCarBrandCrudRepository.findById(id)
                .map(iCardBrandMapper::toMarcaCochePojo);
        // carBrandEntity -> iCardBrandMapper.toMarcaCochePojo(carBrandEntity) - LAMBDA
        // iCardBrandMapper::toMarcaCochePojo - Metodo por referencia
    }

    /**
     * Guarda una nueva MarcaCochePojo
     * @param newCarBrand marca coche Pojo a guardar
     * @return MarcaCochePojo guardada
     */
    @Override
    public CarBrandPojo save(CarBrandPojo newCarBrand) {
        CarBrandEntity carBrandEntity = iCardBrandMapper.toMarcaCocheEntity(newCarBrand);
        return iCardBrandMapper.toMarcaCochePojo(iCardCarBrandCrudRepository.save(carBrandEntity));
    }


    /**
     * Elimina una marca coche dada su id
     * @param idCarBrand marca coche a eliminar
     */
    @Override
    public void delete(Integer idCarBrand) {
         iCardCarBrandCrudRepository.deleteById(idCarBrand);
    }
}
