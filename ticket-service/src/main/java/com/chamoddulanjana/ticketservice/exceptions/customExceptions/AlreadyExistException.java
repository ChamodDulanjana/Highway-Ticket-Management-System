package com.chamoddulanjana.ticketservice.exceptions.customExceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
