package com.chamoddulanjana.ticketservice.exceptions.customExceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
