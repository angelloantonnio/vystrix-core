package com.vystrix.core.application.exception;

public class ForbiddenOperationException extends RuntimeException {
    public ForbiddenOperationException(String message){
        super(message);
    }

    public ForbiddenOperationException(){
        super("Você não tem permissão para executar esta operação");
    }
}
