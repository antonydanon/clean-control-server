package com.bsuir.clean_control_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class WorkDayDTO {
    private Timestamp startingTime;
    private Timestamp endingTime;
}
