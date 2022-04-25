package com.bsuir.clean_control_server.controller;

import com.bsuir.clean_control_server.dto.WorkDayIntervalDTO;
import com.bsuir.clean_control_server.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
@Slf4j
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/workDay")
    public ResponseEntity<WorkDayIntervalDTO> getManagerWorkDay(@RequestParam String phone) {
        log.info("Getting manager work day by phone: " + phone);
        return ResponseEntity.ok().body(managerService.getWorkDayInterval(phone));
    }
}
