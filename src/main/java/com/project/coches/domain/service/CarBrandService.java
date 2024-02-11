package com.project.coches.domain.service;

import com.project.coches.domain.dto.CarBrandDto;
import com.project.coches.domain.repository.ICarBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de marca coche
 */
@RequiredArgsConstructor
@Service
public class CarBrandService implements ICarBrandService {

    /**
     * Repositorio de marca coche
     */
    private final ICarBrandRepository iCarBrandRepository;

    /**
     * Devuelve una lista con todas las marcas de coche
     * @return lista con marcas de coches
     */
    @Override
    public List<CarBrandDto> getAll() {
        return iCarBrandRepository.getAll();
    }

    /**
     * Consulta una marca de coche dada su id
     * @param id parametro id a constultar
     * @return Devuelve una marca de coche
     */
    @Override
    public Optional<CarBrandDto> getCarBrand(Integer id) {
        return iCarBrandRepository.getCarBrand(id);
    }

    /**
     * Guarda una nueva marca coche
     * @param newCarBrand marca coche a guardar
     * @return marca coche guardada
     */
    @Override
    public CarBrandDto save(CarBrandDto newCarBrand) {
        return iCarBrandRepository.save(newCarBrand);
    }

    /**
     * Actualiza una marca coche
     * @param newCarBrand marca coche a actualizar
     * @return Optional con coche actualizado
     */
    @Override
    public Optional<CarBrandDto> update(CarBrandDto newCarBrand) {

        if (iCarBrandRepository.getCarBrand(newCarBrand.getId()).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(iCarBrandRepository.save(newCarBrand));
    }

    /**
     * Elimina una marca coche dada su ID
     * @param idCarBrand marca coche a eliminar
     * @return true si se eliminó y falso de lo  contrario
     */
    @Override
    public boolean delete(Integer idCarBrand) {

        if (iCarBrandRepository.getCarBrand(idCarBrand).isEmpty()) {
            return false;
        }
        iCarBrandRepository.delete(idCarBrand);
        return true;
    }
}
