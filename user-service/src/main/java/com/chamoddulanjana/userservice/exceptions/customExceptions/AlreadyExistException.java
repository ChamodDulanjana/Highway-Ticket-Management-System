package com.chamoddulanjana.userservice.exceptions.customExceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
