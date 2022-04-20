package com.bsuir.clean_control_server.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ResourceNotFoundException extends ExceptionWithHttpStatus{
    public ResourceNotFoundException(String message) {
        super(NOT_FOUND, message);
    }
}
