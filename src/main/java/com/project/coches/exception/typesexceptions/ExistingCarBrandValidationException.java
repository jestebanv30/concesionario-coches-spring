package com.project.coches.exception.typesexceptions;

/**
 * Excepción para la existencia del coche
 */
public class ExistingCarBrandValidationException extends RuntimeException {
    public ExistingCarBrandValidationException() {
        super("La marca de coche ya existe.");
    }
}
