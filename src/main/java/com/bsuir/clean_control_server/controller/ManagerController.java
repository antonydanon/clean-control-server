package com.bsuir.clean_control_server.controller;

import com.bsuir.clean_control_server.dto.WorkDayIntervalDTO;
import com.bsuir.clean_control_server.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/workDay")
    public ResponseEntity<WorkDayIntervalDTO> getManagerWorkDay(@RequestParam String phone) {
        return ResponseEntity.ok().body(managerService.getWorkDayInterval(phone));
    }
}
