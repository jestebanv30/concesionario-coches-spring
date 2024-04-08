package com.project.coches.exception.typesexceptions;

/**
 * Excepción para repetición de Email
 */
public class ExistingEmailCustomerException extends RuntimeException {
    public ExistingEmailCustomerException(){
        super("El email ya está asociado a una cuenta");
    }
}
