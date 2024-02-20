package com.project.coches.exception;

import com.project.coches.exception.typesexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class ControllersExceptions {

    @ExceptionHandler({EmailValidationException.class, ExceptionCardIdNonExistingCustomer.class,
            ExistingCardIDCustomerException.class, ExistingCardIDCustomerException.class,
            ExistingEmailCustomerException.class, NoExistCarException.class,
            ValidationOfNonExistentCarBrand.class, ValidationOfNonExistentCustomer.class })
    public ProblemDetail validationExceptions (RuntimeException exception){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
