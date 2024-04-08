package com.project.coches.exception.typesexceptions;

/**
 * Excepción para repetición de cedula
 */
public class ExistingCardIDCustomerException extends RuntimeException {
    public ExistingCardIDCustomerException() {
        super("La cédula ya está asociada a un cliente");
    }
}
