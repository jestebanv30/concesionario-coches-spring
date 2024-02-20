package com.project.coches.domain.useCase;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.ResponseCustomerDto;

import java.util.List;
import java.util.Optional;

public interface ICustomerUseCase {
    List<CustomerDto> getAll();

    Optional<CustomerDto> getCustomerByCardID(String cardId);

    Optional<CustomerDto> getCustomerByEmail(String email);

    ResponseCustomerDto save(CustomerDto customerDtoNew);

    Optional<CustomerDto> update(CustomerDto customerDtonew);

    boolean delete(String cardId);
}
