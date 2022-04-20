package com.bsuir.clean_control_server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionWithHttpStatus {
    private HttpStatus status;
    private String message;
}
