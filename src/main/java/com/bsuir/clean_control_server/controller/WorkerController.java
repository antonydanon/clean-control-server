package com.bsuir.clean_control_server.controller;

import com.bsuir.clean_control_server.dto.QuittersDTO;
import com.bsuir.clean_control_server.dto.ShowingBroadcastDTO;
import com.bsuir.clean_control_server.dto.WorkerDTO;
import com.bsuir.clean_control_server.dto.SendLocationDTO;
import com.bsuir.clean_control_server.dto.ReceiveLocationDTO;
import com.bsuir.clean_control_server.service.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
@RequiredArgsConstructor
@Slf4j
public class WorkerController {
    private final WorkerService workerService;

    @PostMapping("/newLocation")
    public ResponseEntity<ReceiveLocationDTO> newLocation(@RequestBody ReceiveLocationDTO receiveLocationDTO) {
        log.info("Updating location of worker with phone = " + receiveLocationDTO.getPhoneNumber());
        return new ResponseEntity<>(workerService.newLocation(receiveLocationDTO), HttpStatus.OK);
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<List<WorkerDTO>> getAllWorkersFromOrder(@PathVariable Long orderId) {
        log.info("Getting all workers of order id = " + orderId);
        return ResponseEntity.ok().body(workerService.getListOfWorkersDTO(orderId));
    }

    @GetMapping("/location/{workerId}")
    public ResponseEntity<SendLocationDTO> getWorkerLocation(@PathVariable Long workerId) {
        log.info("Getting location of worker ID = " + workerId);
        return ResponseEntity.ok().body(workerService.getWorkerLocation(workerId));
    }

    @GetMapping("/quitters")
    public ResponseEntity<List<QuittersDTO>> getQuitters(@RequestParam String phone) {
        log.info("Getting quitters of manager with phone: " + phone);
        return ResponseEntity.ok().body(workerService.getQuitters(phone));
    }

    @PostMapping("/broadcast")
    public ResponseEntity<ShowingBroadcastDTO> updateShowingBroadcast(@RequestBody ShowingBroadcastDTO showingBroadcastDTO) {
        log.info("Updating showingBroadcast of worker with id = " + showingBroadcastDTO.getWorkerId() + " to " + showingBroadcastDTO.getShowingBroadcast());
        return ResponseEntity.ok().body(workerService.updateShowingBroadcast(showingBroadcastDTO));
    }
}
