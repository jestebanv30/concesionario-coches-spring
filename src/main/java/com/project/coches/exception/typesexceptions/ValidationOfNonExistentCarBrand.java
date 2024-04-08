package com.project.coches.exception.typesexceptions;

/**
 * Excepción para la inexistencia de la marca del coche
 */
public class ValidationOfNonExistentCarBrand extends RuntimeException {
    public ValidationOfNonExistentCarBrand() {
        super("La marca de coche no existe.");
    }
}