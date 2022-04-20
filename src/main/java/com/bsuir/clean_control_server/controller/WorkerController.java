package com.bsuir.clean_control_server.controller;

import com.bsuir.clean_control_server.dto.SendLocationDTO;
import com.bsuir.clean_control_server.dto.ReceiveLocationDTO;
import com.bsuir.clean_control_server.model.Worker;
import com.bsuir.clean_control_server.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;

    @PostMapping("/newLocation")
    public ResponseEntity<?> newLocation(@RequestBody ReceiveLocationDTO receiveLocationDTO) {
        return new ResponseEntity<>(workerService.newLocation(receiveLocationDTO), HttpStatus.OK);
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<List<Worker>> getAllWorkersFromOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(workerService.getAllWorkersByOrderId(orderId));
    }

    @GetMapping("/location/{workerId}")
    public ResponseEntity<SendLocationDTO> getWorkerLocation(@PathVariable Long workerId) {
        return ResponseEntity.ok().body(workerService.getWorkerLocation(workerId));
    }
}
