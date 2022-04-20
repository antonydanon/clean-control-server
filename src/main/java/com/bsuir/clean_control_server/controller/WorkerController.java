package com.bsuir.clean_control_server.controller;

import com.bsuir.clean_control_server.dto.WorkerLocation;
import com.bsuir.clean_control_server.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workers")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;

    @PostMapping("/newLocation")
    public ResponseEntity<?> newLocation(@RequestBody WorkerLocation workerLocation) {
        return new ResponseEntity<>(workerService.newLocation(workerLocation), HttpStatus.OK);
    }

}
