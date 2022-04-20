package com.bsuir.clean_control_server.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class UserNotFoundException extends ExceptionWithHttpStatus{
    public UserNotFoundException(String message) {
        super(NOT_FOUND, message);
    }
}
