package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.exception.ResourceNotFoundException;
import com.bsuir.clean_control_server.model.Manager;
import com.bsuir.clean_control_server.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public Manager getManagerByPhone(String phone) {
        return managerRepository.getManagerByPhoneNumber(phone).orElseThrow(() ->
           new ResourceNotFoundException("Manager with phone: " + phone + " doesn't exists in database"));
    }
}
