package com.project.coches.exception.typesexceptions;

/**
 * Excepción para el formato de Email
 */
public class EmailValidationException extends RuntimeException {
    public EmailValidationException(){
        super("El email no tiene el formato requerido.");
    }
}
