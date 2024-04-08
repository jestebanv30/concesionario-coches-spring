package com.project.coches.exception.typesexceptions;

/**
 * Excepción para la existencia del cliente
 */
public class ValidationOfNonExistentCustomer extends RuntimeException {
    public ValidationOfNonExistentCustomer(){
        super("El cliente no existe.");
    }
}
