package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.LoginRequestDTO;
import com.bsuir.clean_control_server.dto.LoginResponseDTO;
import com.bsuir.clean_control_server.exception.InvalidPasswordException;
import com.bsuir.clean_control_server.model.Manager;
import com.bsuir.clean_control_server.model.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public LoginResponseDTO loginManager(LoginRequestDTO loginRequestDTO){
        Manager manager = managerService.getManagerByPhoneNumber(loginRequestDTO.getPhoneNumber());
        if(!loginRequestDTO.getPassword().equals(manager.getPassword()))
            throw new InvalidPasswordException("Invalid password");
        return new LoginResponseDTO(new Date(), "Добро пожаловать!");
    }

    public LoginResponseDTO createNegativeResponse() {
        return new LoginResponseDTO(new Date(), "Неверные логин/пароль");
    }
}
