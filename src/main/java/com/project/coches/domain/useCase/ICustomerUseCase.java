package com.project.coches.domain.useCase;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.ResponseCustomerDto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de caso de uso para cliente
 */
public interface ICustomerUseCase {

    /**
     * Obtiene una lista de cliente
     * @return lista de cliente
     */
    List<CustomerDto> getAll();

    /**
     * Busca un cliente dada su id
     * @param cardId id a buscar
     * @return cliente encontrado
     */
    Optional<CustomerDto> getCustomerByCardID(String cardId);

    /**
     * Busca un cliente dado su email
     * @param email email a buscar
     * @return cliente encontrado
     */
    Optional<CustomerDto> getCustomerByEmail(String email);

    /**
     * Guarda un cliente
     * @param customerDtoNew cliente nuevo
     * @return cliente guardado
     */
    ResponseCustomerDto save(CustomerDto customerDtoNew);

    /**
     * Actualiza un cliente
     * @param customerDtoNew cliente a actualizar
     * @return cliente actualizado
     */
    Optional<CustomerDto> update(CustomerDto customerDtoNew);

    /**
     * Elimina un cliente
     * @param cardId cliente a eliminar
     * @return cliente eliminado
     */
    boolean delete(String cardId);
}
