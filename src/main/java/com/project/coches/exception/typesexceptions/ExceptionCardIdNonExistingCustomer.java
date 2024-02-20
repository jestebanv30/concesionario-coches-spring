package com.project.coches.exception.typesexceptions;

public class ExceptionCardIdNonExistingCustomer extends RuntimeException {

    public ExceptionCardIdNonExistingCustomer(){
        super("La cedula a digitar es incorrecta.");
    }
}
