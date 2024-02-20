package com.project.coches.exception.typesexceptions;

public class NoExistCarException extends RuntimeException {

    public NoExistCarException(){
        super("El carro no existe.");
    }

}
