package com.project.coches.exception.typesexceptions;

public class ValidationOfNonExistentCustomer extends RuntimeException {

    public ValidationOfNonExistentCustomer(){
        super("El cliente no existe.");
    }
}
