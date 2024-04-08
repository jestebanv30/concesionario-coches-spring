package com.project.coches.persistance.repository;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.persistance.crud.ICarCrudRepository;
import com.project.coches.persistance.entity.CarEntity;
import com.project.coches.persistance.mapper.ICarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de coche
 */
@RequiredArgsConstructor
@Repository
public class CarRepository implements ICarRepository {

    private final ICarCrudRepository iCarCrudRepository;

    private final ICarMapper iCarMapper;

    /**
     * Lista de CochesDto a través de listar CochesEntity
     * @return Lista de CochesDto
     */
    @Override
    public List<CarDto> getAll() {
        return iCarMapper.toCarsDto(iCarCrudRepository.findAll());
    }

    /**
     * Lista de cochesDto que pertenezcan a una marcaCoche
     * @param carBrandId id de marca coche a buscar
     * @return Lista de cochesDto perteneciente a la marcaCoche
     */
    @Override
    public List<CarDto> getByIdCarBrand(Integer carBrandId) {
        return iCarMapper.toCarsDto(iCarCrudRepository.findAllByCarBrandId(carBrandId));
    }

    /**
     * Lista de cochesDto con precio menor o igual a price
     * @param price precio de referencia
     * @return Lista de cochesDto que cumplan con el requisito
     */
    @Override
    public List<CarDto> getCarByPriceLessThan(Double price) {
        return iCarMapper.toCarsDto(iCarCrudRepository.findAllByPriceLessThanEqualOrderByPriceDesc(price));
    }

    /**
     * Lista de cochesDto con precio mayor o igual a price
     * @param price precio de referencia
     * @return Lista de cochesDto que cumplan con el requisito
     */
    @Override
    public List<CarDto> getCarByPriceGreaterThan(Double price) {
        return iCarMapper.toCarsDto(iCarCrudRepository.findAllByPriceGreaterThanEqualOrderByPriceAsc(price));
    }

    /**
     * Busca un cocheEntity y la transformo en Dto
     * @param idCar id de coche a buscar
     * @return una CocheDto encontrado
     */
    @Override
    public Optional<CarDto> getCarById(Integer idCar) {
        return iCarCrudRepository.findById(idCar).map(iCarMapper::toCarDto);
    }

    /**
     * Guardar una cocheDto y para la BD una cocheEntity
     * @param newCarDto cocheDto nueva
     * @return MarcaCocheDto guardada
     */
    @Override
    public CarDto save(CarDto newCarDto) {

        CarEntity carEntity = iCarCrudRepository.save(iCarMapper.toCarEntity(newCarDto));

        return iCarMapper.toCarDto(iCarCrudRepository.save(carEntity));
    }

    /**
     * Elimina un coche en la bd
     * @param idCar id de coche a eliminar
     */
    @Override
    public void delete(Integer idCar) {
        iCarCrudRepository.deleteById(idCar);
    }
}
