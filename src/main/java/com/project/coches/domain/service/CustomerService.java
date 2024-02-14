package com.project.coches.domain.service;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.ResponseCurstomerDto;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.exception.EmailValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerService{

    private  final ICustomerRepository iCustomerRepository;

    @Override
    public List<CustomerDto> getAll() {
        return iCustomerRepository.getAll();
    }

    @Override
    public Optional<CustomerDto> getCustomerDtoById(String id) {
        return iCustomerRepository.getCustomerByCardId(id);
    }

    @Override
    public Optional<CustomerDto> getCustomerByEmail(String email) {
        return iCustomerRepository.getCustomerByEmail(email);
    }

    @Override
    public ResponseCurstomerDto save(CustomerDto newCustomer) {

        /**
         * Si cumple con la expresion regular es que el @....com esta bien, por eso esta negada al principio
         */
        if (!newCustomer.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")){
            throw new EmailValidationException();
        }

        String passwordGenerate = generateRandomPassword(8);
        newCustomer.setPassword(passwordGenerate);
        newCustomer.setActive(1);

        iCustomerRepository.save(newCustomer);

        return new ResponseCurstomerDto(passwordGenerate);
    }

    @Override
    public Optional<CustomerDto> update(CustomerDto newCustomer) {
        if (iCustomerRepository.getCustomerByCardId(newCustomer.getCardId()).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(iCustomerRepository.save(newCustomer));
    }

    @Override
    public boolean delete(String idCarBrand) {
        if (iCustomerRepository.getCustomerByCardId(idCarBrand).isEmpty()) {
            return false;
        }
        iCustomerRepository.delete(idCarBrand);
        return true;
    }

    private String generateRandomPassword(int len){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefhijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }
}
