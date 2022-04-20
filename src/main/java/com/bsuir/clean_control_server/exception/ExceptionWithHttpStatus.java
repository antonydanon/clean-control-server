package com.bsuir.clean_control_server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExceptionWithHttpStatus extends RuntimeException{
    private HttpStatus status;
    private String message;
}
