package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.LoginRequestDTO;
import com.bsuir.clean_control_server.exception.InvalidPasswordException;
import com.bsuir.clean_control_server.model.Manager;
import com.bsuir.clean_control_server.model.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ManagerService managerService;
    private final WorkerService workerService;

    public Worker loginWorker(LoginRequestDTO loginRequestDTO){
        Worker worker = workerService.getWorkerByPhoneNumber(loginRequestDTO.getPhoneNumber());
        if(!loginRequestDTO.getPassword().equals(worker.getPassword()))
            throw new InvalidPasswordException("Invalid password");
        return worker;
    }

    public Manager loginManager(LoginRequestDTO loginRequestDTO){
        Manager manager = managerService.getManagerByPhoneNumber(loginRequestDTO.getPhoneNumber());
        if(!loginRequestDTO.getPassword().equals(manager.getPassword()))
            throw new InvalidPasswordException("Invalid password");
        return manager;
    }

    public Map<Object, Object> createNegativeResponse() {
        Map<Object, Object> response = new HashMap<>();
        response.put("date", new Date());
        response.put("message", "Invalid credentials.");
        return response;
    }
}
