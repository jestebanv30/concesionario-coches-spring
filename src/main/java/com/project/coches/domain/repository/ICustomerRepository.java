package com.project.coches.domain.repository;

import com.project.coches.domain.dto.CarBrandDto;
import com.project.coches.domain.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {

    List<CustomerDto> getAll();

    Optional<CustomerDto> getCustomerByCardID(String cardId);

    Optional<CustomerDto> getCustomerByEmail(String email);

    CustomerDto save(CustomerDto customerDtoNew);

    void delete(String cardId);
}
