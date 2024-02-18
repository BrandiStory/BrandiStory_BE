package com.supercoding.brandiStory.service.exceptions;

//@ResponseStatus(HttpStatus.BAD_REQUEST)

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);
    }
}
