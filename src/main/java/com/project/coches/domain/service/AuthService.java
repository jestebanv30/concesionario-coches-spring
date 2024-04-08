package com.project.coches.domain.service;

import com.project.coches.domain.dto.AuthCustomerDto;
import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.JwtResponseDto;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.domain.useCase.IAuthUseCase;
import com.project.coches.exception.typesexceptions.PasswordIncorrectException;
import com.project.coches.exception.typesexceptions.ValidationOfNonExistentCustomer;
import com.project.coches.security.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthUseCase {

    private final ICustomerRepository iCustomerRepository;

    /**
     * Clase que administra los JWTs
     */
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * Clase que encripta conteraseñas
     */
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponseDto signIn(AuthCustomerDto authCustomerDto) {

        Optional<CustomerDto> emailExisting = iCustomerRepository.getCustomerByEmail(authCustomerDto.getEmail());

        if (emailExisting.isEmpty()) {
            throw new ValidationOfNonExistentCustomer();
        }

        if (!passwordEncoder.matches(authCustomerDto.getPassword(), emailExisting.get().getPassword())) {
            throw new PasswordIncorrectException();
        }

        return new JwtResponseDto(jwtAuthenticationProvider.createToken(emailExisting.get()));
    }

    @Override
    public JwtResponseDto signOut(String jwt) {

        String[] authElements = jwt.split(" ");

        return new JwtResponseDto(jwtAuthenticationProvider.deleteToken(authElements[1]));
    }
}
