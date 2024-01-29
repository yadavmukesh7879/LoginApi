package com.test.exception;

import org.springframework.http.HttpStatus;

public class JWTAuthenticationException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public JWTAuthenticationException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
