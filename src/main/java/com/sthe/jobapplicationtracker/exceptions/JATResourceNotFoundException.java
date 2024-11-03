package com.sthe.jobapplicationtracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JATResourceNotFoundException extends RuntimeException{
    public JATResourceNotFoundException(String message) {
        super(message);
    }
}
