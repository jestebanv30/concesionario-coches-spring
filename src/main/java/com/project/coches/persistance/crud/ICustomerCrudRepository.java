package com.project.coches.persistance.crud;

import com.project.coches.persistance.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerCrudRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByEmailIgnoreCase(String email);
}
