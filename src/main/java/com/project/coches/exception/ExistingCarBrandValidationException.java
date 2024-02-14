package com.project.coches.exception;

public class ExistingCarBrandValidationException extends RuntimeException {

    public ExistingCarBrandValidationException() {
        super("La marca de coche ya existe.");
    }
}
