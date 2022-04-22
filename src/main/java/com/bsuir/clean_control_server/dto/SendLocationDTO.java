package com.bsuir.clean_control_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendLocationDTO {
    private double latitude;
    private double longitude;
    private boolean isInsideZone;
}
