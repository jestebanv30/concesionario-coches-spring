package com.project.coches.exception.typesexceptions;

public class ExistingEmailCustomerException extends RuntimeException {

    public ExistingEmailCustomerException(){
        super("El email ya está asociado a una cuenta");
    }
}
