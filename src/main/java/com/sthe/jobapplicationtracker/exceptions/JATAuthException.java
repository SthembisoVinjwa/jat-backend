package com.sthe.jobapplicationtracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
public class JATAuthException extends RuntimeException {
    public JATAuthException(String message) {
        super(message);
    }
}
