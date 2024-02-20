package com.project.coches.persistance.mapper;

import com.project.coches.domain.dto.CarBrandDto;
import com.project.coches.persistance.entity.CarBrandEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper que transforma obj de MarcaCoche a pojos o entidades
 */
@Mapper(componentModel = "spring")
public interface ICarBrandMapper {

    /**
     * Mappea la entidad a pojo de MarcaCoche
     *
     * @param marcaEntity Entidad a convertir
     * @return Pojo
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    CarBrandDto toMarcaCocheDto(CarBrandEntity marcaEntity);

    /**
     * Convierte el Pojo a Entidad de MarcaCoche
     *
     * @param marcaPojo Pojo a convertir
     * @return Entidad
     */
    @InheritInverseConfiguration
    CarBrandEntity toMarcaCocheEntity(CarBrandDto marcaPojo);

    /**
     * Retorna una lista de marcasCoche transformada a pojo de una lista de entidades
     *
     * @param marcasCocheEntity Lista Entidades a transformar
     * @return Lista Pojo
     */
    List<CarBrandDto> toMarcasCocheDto(List<CarBrandEntity> marcasCocheEntity);


}
