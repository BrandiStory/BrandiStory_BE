package com.supercoding.brandiStory.service.exceptions;

//@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptException extends RuntimeException {
    public NotAcceptException(String message) {
        super(message);
    }
}
