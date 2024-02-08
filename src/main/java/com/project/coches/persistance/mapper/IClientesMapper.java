package com.project.coches.persistance.mapper;

import com.project.coches.domain.pojo.ClientesPojo;
import com.project.coches.persistance.entity.ClientesEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IClientesMapper {

    ClientesPojo toClientesPojo(ClientesEntity clientesEntity);

    ClientesEntity toClientesEntity(ClientesPojo clientesPojo);

    List<ClientesPojo> toClientesPojoList(List<ClientesEntity> toClientesEntityList);
}
