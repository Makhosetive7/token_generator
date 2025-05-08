package com.example.ElectricityTokenGenerator.exceptionHandling.TokensException;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super("Insufficient Balance: " + message);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super("Insufficient Balance: " + message, cause);
    }
    
}
