package com.bsuir.clean_control_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShowingBroadcastDTO {
    private Long workerId;
    private Boolean showingBroadcast;
}
