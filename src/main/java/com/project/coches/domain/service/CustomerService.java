package com.project.coches.domain.service;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.ResponseCustomerDto;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.domain.useCase.ICustomerUseCase;
import com.project.coches.exception.typesexceptions.*;
import com.project.coches.security.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de cliente
 */
@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerUseCase {

    private final ICustomerRepository iCustomerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<CustomerDto> getAll() {
        return iCustomerRepository.getAll();
    }

    @Override
    public Optional<CustomerDto> getCustomerByCardID(String cardId) {

        Optional<CustomerDto> existingId = iCustomerRepository.getCustomerByCardID(cardId);

        if (existingId.isEmpty()) {
            throw new ValidationOfNonExistentCustomer();
        }
        return iCustomerRepository.getCustomerByCardID(cardId);
    }

    @Override
    public Optional<CustomerDto> getCustomerByEmail(String email) {

        Optional<CustomerDto> existingEmail = iCustomerRepository.getCustomerByEmail(email);

        if (existingEmail.isEmpty()) {
            throw new ValidationOfNonExistentCustomer();
        }
        return iCustomerRepository.getCustomerByEmail(email);
    }

    @Override
    public ResponseCustomerDto save(CustomerDto customerDtoNew) {

        validateCustomerDto(customerDtoNew);

        String passwordGenerate = generateRandomPassword(8);
        customerDtoNew.setPassword(passwordEncoder.encode(passwordGenerate));
        customerDtoNew.setActive(1);
        customerDtoNew.setRol(Roles.CUSTOMER);
        iCustomerRepository.save(customerDtoNew);

        return new ResponseCustomerDto(passwordGenerate);
    }

    @Override
    public Optional<CustomerDto> update(CustomerDto customerDtoNew) {

        Optional<CustomerDto> existingId =  iCustomerRepository.getCustomerByCardID(customerDtoNew.getCardId());

        if (existingId.isEmpty()) {
            throw new ExceptionCardIdNonExistingCustomer();
        }

        Optional<CustomerDto> existingEmail = iCustomerRepository.getCustomerByEmail(customerDtoNew.getEmail());

        if (existingEmail.isPresent() && !existingEmail.get().getCardId().equals(customerDtoNew.getCardId())) {
            throw new ExistingEmailCustomerException();
        }

        customerDtoNew.setPassword(passwordEncoder.encode(customerDtoNew.getPassword()));

        return Optional.of(iCustomerRepository.save(customerDtoNew));

    }

    @Override
    public boolean delete(String cardId) {

        Optional<CustomerDto> existingId = iCustomerRepository.getCustomerByCardID(cardId);

        if (existingId.isEmpty()) {
            throw new ExceptionCardIdNonExistingCustomer();
        }
        iCustomerRepository.delete(cardId);
        return true;
    }

    /**
     * Generador de contraseña
     * @param len cantidad de caracteres de la contraseña
     * @return contraseña generada
     */
    private String generateRandomPassword(int len) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefhijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    private void validateCustomerDto(CustomerDto customerDto){
        // Validar cédula
        if (customerDto.getCardId() == null || customerDto.getCardId().length() < 8 || customerDto.getCardId().length() > 12) {
            throw new IllegalArgumentException("La cédula debe tener un valor numérico mínimo de 8 y máximo de 12 dígitos");
        }

        // Validar nombre completo
        if (customerDto.getFullName() == null || customerDto.getFullName().length() <= 10 || customerDto.getFullName().length() >= 40) {
            throw new IllegalArgumentException("El nombre completo debe ser una cadena de caracteres mayor a 10 y menor a 40");
        }

        // Validar correo
        if (customerDto.getEmail() == null || customerDto.getEmail().length() <= 6 || customerDto.getEmail().length() > 25) {
            throw new IllegalArgumentException("El correo debe ser una cadena de caracteres mayor a 6 y menor o igual a 25");
        }

        // Validar número celular
        if (customerDto.getNumberCellphone() == null || customerDto.getNumberCellphone().toString().length() != 10) {
            throw new IllegalArgumentException("El número celular debe ser un valor numérico de 10 dígitos");
        }

        if (!customerDto.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new EmailValidationException();
        }

        Optional<CustomerDto> existingId = iCustomerRepository.getCustomerByCardID(customerDto.getCardId());

        if (existingId.isPresent()) {
            throw new ExistingCardIDCustomerException();
        }

        Optional<CustomerDto> existingEmail = iCustomerRepository.getCustomerByEmail(customerDto.getEmail());

        if (existingEmail.isPresent()) {
            throw new ExistingEmailCustomerException();
        }
    }
}
