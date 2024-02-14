package com.project.coches.domain.repository;

import com.project.coches.domain.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio de cliente
 */
public interface ICustomerRepository {

    /**
     * Devuelve una lista con todos los clientes
     * @return lista con clientes
     */
    List<CustomerDto> getAll();

    /**
     * Consulta un cliente dada su id
     * @param cardId parametro id a constultar
     * @return Devuelve un cliente
     */
    Optional<CustomerDto> getCustomerByCardId(String cardId);

    /**
     * Consulta un cliente dado su email
     * @param email email a consultar
     * @return cliente con email encontrado
     */
    Optional<CustomerDto> getCustomerByEmail(String email);

    /**
     * Guarda un cliente
     * @param customerDto cliente a guardar
     * @return cliente guardado
     */
    CustomerDto save(CustomerDto customerDto);

    /**
     * Elimina un cliente
     * @param cardId id de cliente a liminar
     */
    void delete(String cardId);
}
