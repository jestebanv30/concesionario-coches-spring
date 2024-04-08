package com.project.coches.exception.typesexceptions;

/**
 * Excepción para la inexistencia de la cédula
 */
public class ExceptionCardIdNonExistingCustomer extends RuntimeException {
    public ExceptionCardIdNonExistingCustomer(){
        super("La cedula a digitar es incorrecta.");
    }
}
