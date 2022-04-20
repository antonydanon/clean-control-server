package com.bsuir.clean_control_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ReceiveLocationDTO {
    private String phoneNumber;
    private double longitude;
    private double latitude;
}
