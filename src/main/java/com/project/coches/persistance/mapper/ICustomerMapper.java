package com.project.coches.persistance.mapper;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.persistance.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * Mapper que transforma obj de Cliente a Dto o entidades
 */
@Mapper(componentModel = "spring")
public interface ICustomerMapper {

    CustomerDto toCustomerDto(CustomerEntity customerEntity);

    @Mapping(target = "purchaseEntities", ignore = true)
    CustomerEntity toCustomerEntity(CustomerDto customerDto );

    List<CustomerDto> toCustomersDto (List<CustomerEntity> customerEntityList);
}
