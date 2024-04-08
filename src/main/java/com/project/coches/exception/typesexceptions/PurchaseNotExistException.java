package com.project.coches.exception.typesexceptions;

public class PurchaseNotExistException extends RuntimeException {
    public PurchaseNotExistException(){
        super("La factura no esiste.");
    }
}
