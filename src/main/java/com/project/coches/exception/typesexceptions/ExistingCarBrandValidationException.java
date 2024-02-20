package com.project.coches.exception.typesexceptions;

public class ExistingCarBrandValidationException extends RuntimeException {

    public ExistingCarBrandValidationException() {
        super("La marca de coche ya existe.");
    }
}
