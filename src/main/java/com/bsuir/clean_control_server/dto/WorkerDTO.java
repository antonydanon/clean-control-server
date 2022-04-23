package com.bsuir.clean_control_server.dto;

import com.bsuir.clean_control_server.model.Worker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkerDTO {
    private Worker worker;
    private boolean isWorking;
}
