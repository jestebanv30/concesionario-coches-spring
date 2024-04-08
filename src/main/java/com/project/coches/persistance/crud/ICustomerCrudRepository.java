package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interfaz de crud en la base de datos para cliente
 */
public interface ICustomerCrudRepository extends JpaRepository<CustomerEntity, String> {

    /**
     * Busca un cliente dado su email ignorando mayúsculas y minúsculas
     * @param email email a buscar
     * @return cliente encontrado
     */
    Optional<CustomerEntity> findByEmailIgnoreCase(String email);
}
