package com.project.coches.persistance.repository;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.persistance.crud.ICustomerCrudRepository;
import com.project.coches.persistance.entity.CustomerEntity;
import com.project.coches.persistance.mapper.ICustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de cliente
 */
@RequiredArgsConstructor
@Repository
public class CustomerRepository implements ICustomerRepository {

    private final ICustomerCrudRepository iCustomerCrudRepository;

    private final ICustomerMapper iCustomerMapper;

    /**
     * Lista de clienteDto a través de listar clienteEntity
     * @return Lista de clienteDto
     */
    @Override
    public List<CustomerDto> getAll() {
        return iCustomerMapper.toCustomersDto(iCustomerCrudRepository.findAll());
    }

    /**
     * Busca un clienteEntity y la transforma en Dto
     * @param cardId id de cliente a buscar
     * @return un clienteDto encontrado
     */
    @Override
    public Optional<CustomerDto> getCustomerByCardID(String cardId) {
        return iCustomerCrudRepository.findById(cardId)
                .map(iCustomerMapper::toCustomerDto);
    }

    /**
     * Busca un email existente de cliente
     * @param email email a buscar
     * @return email encontrada
     */
    @Override
    public Optional<CustomerDto> getCustomerByEmail(String email) {
        return iCustomerCrudRepository.findByEmailIgnoreCase(email)
                .map(iCustomerMapper::toCustomerDto);
    }

    /**
     * Guardar un clienteDto y para la BD un clienteEntity
     * @param customerDtoNew clienteDto nuevo
     * @return clienteDto guardada
     */
    @Override
    public CustomerDto save(CustomerDto customerDtoNew) {

        CustomerEntity customerEntity = iCustomerCrudRepository.save(iCustomerMapper.toCustomerEntity(customerDtoNew));

        return iCustomerMapper.toCustomerDto(customerEntity);
    }

    /**
     * ELimmina un cliente en la bd
     * @param cardId id de ciente a eliminar
     */
    @Override
    public void delete(String cardId) {
        iCustomerCrudRepository.deleteById(cardId);
    }
}
