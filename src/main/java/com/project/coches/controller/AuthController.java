package com.project.coches.controller;

import com.project.coches.domain.dto.AuthCustomerDto;
import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.JwtResponseDto;
import com.project.coches.domain.dto.ResponseCustomerDto;
import com.project.coches.domain.useCase.IAuthUseCase;
import com.project.coches.domain.useCase.ICustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final IAuthUseCase iAuthUseCase;

    private final ICustomerUseCase iCustomerUseCase;

    @CrossOrigin(origins = "https://concesionario-coches-front.vercel.app")
    @PostMapping(path = "/register")
    public ResponseEntity<ResponseCustomerDto> register(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCustomerUseCase.save(customerDto));
    }

    @CrossOrigin(origins = "https://concesionario-coches-front.vercel.app")
    @PostMapping(path = "/sign-in")
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody AuthCustomerDto authCustomerDto) {
        return ResponseEntity.ok(iAuthUseCase.signIn(authCustomerDto));
    }

    @CrossOrigin(origins = "https://concesionario-coches-front.vercel.app")
    @PostMapping(path = "/sign-out")
    public ResponseEntity<JwtResponseDto> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(iAuthUseCase.signOut(token));
    }
}
