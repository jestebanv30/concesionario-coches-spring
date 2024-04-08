package com.project.coches.exception.typesexceptions;

public class PasswordIncorrectException extends RuntimeException {

    public PasswordIncorrectException(){
        super("La contraseña es incorrecta.");
    }

}
