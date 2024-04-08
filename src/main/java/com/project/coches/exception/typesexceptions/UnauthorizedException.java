package com.project.coches.exception.typesexceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(){
        super("No tiene los permisos necesarios.");
    }

}
