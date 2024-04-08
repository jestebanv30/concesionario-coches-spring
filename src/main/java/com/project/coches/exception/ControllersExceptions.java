package com.project.coches.exception;

import com.project.coches.exception.typesexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controlador principal de excepciones
 */
@RestControllerAdvice
public class ControllersExceptions {

    @ExceptionHandler({EmailValidationException.class, ExceptionCardIdNonExistingCustomer.class,
            ExistingCardIDCustomerException.class, ExistingCardIDCustomerException.class,
            ExistingEmailCustomerException.class, NoExistCarException.class,
            ValidationOfNonExistentCarBrand.class, ValidationOfNonExistentCustomer.class,
            PasswordIncorrectException.class, PurchaseNotExistException.class})
    public ProblemDetail validationExceptions(RuntimeException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ProblemDetail unauthorizedException(AuthenticationException authenticationException) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, authenticationException.getMessage());
    }


}

/**
 * Antes lo hacía de esta manera, entonces tenía 1 para cada clase
 *
 * @ExceptionHandler(EmailValidationException.class) public ResponseEntity<Map<String, String>> emailValidationException(EmailValidationException emailValidationException) {
 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("ERROR", emailValidationException.getMessage()));
 * }
 *
 *
 *
 * (NO USE)
 * @ExceptionHandler(AccessDeniedException.class)
 *     public ProblemDetail accessControlException(AccessDeniedException accessDeniedException) {
 *         return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, accessDeniedException.getMessage());
 *     }
 */

