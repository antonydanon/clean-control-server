package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.WorkerLocation;
import com.bsuir.clean_control_server.exception.UserNotFoundException;
import com.bsuir.clean_control_server.model.Worker;
import com.bsuir.clean_control_server.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    @Transactional
    public WorkerLocation newLocation(WorkerLocation workerLocation){
        workerRepository.updateLocation(workerLocation.getLatitude(), workerLocation.getLongitude(), workerLocation.getPhoneNumber());
        return workerLocation;
    }

    public Worker findByPhoneNumber(String phoneNumber){
        return workerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new UserNotFoundException("There is no worker with phone number " + phoneNumber + " in database"));
    }

}
