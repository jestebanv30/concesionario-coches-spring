package com.project.coches.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class ControllersExceptions {

    public ResponseEntity<Map<String, String>> existingCarBrandException(ExistingCarBrandValidationException existingCarBrandValidationException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap("ERROR", existingCarBrandValidationException.getMessage()));
    }
}
