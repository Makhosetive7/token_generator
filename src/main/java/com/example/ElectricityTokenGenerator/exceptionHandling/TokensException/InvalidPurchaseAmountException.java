package com.example.ElectricityTokenGenerator.exceptionHandling.TokensException;

public class InvalidPurchaseAmountException extends RuntimeException {
    public InvalidPurchaseAmountException(String message) {
        super("Invalid purchase ammount" + message);
    }

    public InvalidPurchaseAmountException(String message, Throwable cause) {
        super("Invalid purchase ammount" + message, cause);
    }

}
