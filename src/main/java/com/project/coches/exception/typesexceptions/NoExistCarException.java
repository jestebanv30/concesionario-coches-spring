package com.project.coches.exception.typesexceptions;

/**
 * Excepción para existencia del coche
 */
public class NoExistCarException extends RuntimeException {
    public NoExistCarException(){
        super("El carro no existe.");
    }
}
