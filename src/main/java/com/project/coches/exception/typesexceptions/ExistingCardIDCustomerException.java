package com.project.coches.exception.typesexceptions;

public class ExistingCardIDCustomerException extends RuntimeException {

    public ExistingCardIDCustomerException() {
        super("La cédula ya está asociada a un cliente");
    }
}
