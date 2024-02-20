package com.project.coches.exception.typesexceptions;

public class ValidationOfNonExistentCarBrand extends RuntimeException {

    public ValidationOfNonExistentCarBrand() {
        super("La marca de coche no existe.");
    }
}