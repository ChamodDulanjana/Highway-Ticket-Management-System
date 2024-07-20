package com.chamoddulanjana.userservice.exceptions;

import com.chamoddulanjana.userservice.dto.Responses.ErrorResponseInfo;
import com.chamoddulanjana.userservice.exceptions.customExceptions.AlreadyExistException;
import com.chamoddulanjana.userservice.exceptions.customExceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException e) {
        HashMap<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String message = "";
        for (String key : errors.keySet()) {
            message = errors.get(key) + ", ";
        }
        message = message.substring(0, message.length() - 1);
        return new ResponseEntity<>(ErrorResponseInfo.builder().status(HttpStatus.BAD_REQUEST).date(LocalDateTime.now()).message(message).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseInfo> handleException(NotFoundException exception) {
        return new ResponseEntity<>(ErrorResponseInfo.builder().status(HttpStatus.NOT_FOUND).date(LocalDateTime.now()).message(exception.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseInfo> handleException(AlreadyExistException e) {
        return new ResponseEntity<>(ErrorResponseInfo.builder().status(HttpStatus.CONFLICT).date(LocalDateTime.now()).message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }
}
