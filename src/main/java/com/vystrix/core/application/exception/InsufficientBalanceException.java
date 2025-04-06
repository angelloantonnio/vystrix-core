package com.vystrix.core.application.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(){
        super("Saldo insuficiente para realizar esta operação");
    }
}
