package com.project.coches.exception.typesexceptions;

public class EmailValidationException extends RuntimeException {

    public EmailValidationException(){
        super("El email no tiene el formato requerido.");
    }
}
