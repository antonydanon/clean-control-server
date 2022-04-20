package com.bsuir.clean_control_server.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class InvalidPasswordException extends ExceptionWithHttpStatus{
    public InvalidPasswordException(String message) {
        super(UNAUTHORIZED, message);
    }
}
