package com.project.coches.persistance.mapper;

import com.project.coches.domain.dto.CarBrandDto;
import com.project.coches.persistance.entity.CarBrandEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper que transforma obj de MarcaCoche a Dto o entidades
 */
@Mapper(componentModel = "spring")
public interface ICarBrandMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    CarBrandDto toMarcaCocheDto(CarBrandEntity marcaEntity);

    @InheritInverseConfiguration
    CarBrandEntity toMarcaCocheEntity(CarBrandDto marcaPojo);

    List<CarBrandDto> toMarcasCocheDto(List<CarBrandEntity> marcasCocheEntity);

}
