package com.sthe.jobapplicationtracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JATBadRequestException extends RuntimeException {
    public JATBadRequestException(String message) {
        super(message);
    }
}
