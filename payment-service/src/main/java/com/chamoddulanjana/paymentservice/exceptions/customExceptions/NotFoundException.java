package com.chamoddulanjana.paymentservice.exceptions.customExceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
