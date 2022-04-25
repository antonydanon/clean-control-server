package com.bsuir.clean_control_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoStatusDTO {
    private Boolean videoStatus;
    private Long workerId;
}
