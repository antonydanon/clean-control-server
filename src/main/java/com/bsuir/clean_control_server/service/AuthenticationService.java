package com.bsuir.clean_control_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ManagerService managerService;
    private final WorkerService workerService;
}
