package com.example.ElectricityTokenGenerator.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.ElectricityTokenGenerator.exceptionHandling.TokensException.InsufficientBalanceException;
import com.example.ElectricityTokenGenerator.exceptionHandling.TokensException.InvalidKilowattValueException;
import com.example.ElectricityTokenGenerator.exceptionHandling.TokensException.InvalidPurchaseAmountException;
import com.example.ElectricityTokenGenerator.exceptionHandling.TokensException.InvalidTransferException;
import com.example.ElectricityTokenGenerator.exceptionHandling.TokensException.MinimumTransferException;
import com.example.ElectricityTokenGenerator.exceptionHandling.TokensException.TokenExpiredException;
import com.example.ElectricityTokenGenerator.exceptionHandling.UsersException.AccountAlreadyExistsException;
import com.example.ElectricityTokenGenerator.exceptionHandling.UsersException.UserNotFoundException;

@RestControllerAdvice
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    // User not found
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        logger.warn("User not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Account already exists exception
    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<String> handleAccountAlreadyExists(AccountAlreadyExistsException ex) {
        logger.warn("Illegal argument: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Insufficient balance exception
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        logger.warn("Insufficient balance: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //invalid purchase amount exception
    @ExceptionHandler(InvalidPurchaseAmountException.class)
    public ResponseEntity<String> handleInvalidPurchaseAmountException(InvalidPurchaseAmountException ex) {
        logger.warn("Invalid purchase amount: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Invalid Kilowatts exception
    @ExceptionHandler(InvalidKilowattValueException.class)
    public ResponseEntity<String> handleInvalidKilowattsException(InvalidKilowattValueException ex) {
        logger.warn("Invalid kilowatts: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Invalid transfer exception
    @ExceptionHandler(InvalidTransferException.class)
    public ResponseEntity<String> handleInvalidTransferException(InvalidTransferException ex) {
        logger.warn("Invalid transfer: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Minimum transfer exception
    @ExceptionHandler(MinimumTransferException.class)
    public ResponseEntity<String> handleMinimumTransferException(MinimumTransferException ex) {
        logger.warn("Minimum transfer amount not met: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Token Expired exception
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException ex) {
        logger.warn("Token expired: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // 500 - Internal server error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        logger.error("Unhandled exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An internal server error occurred.");
    }
}
