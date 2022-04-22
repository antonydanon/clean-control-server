package com.bsuir.clean_control_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {
    private Date date;
    private String message;
}
