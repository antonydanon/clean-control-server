package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.SendLocationDTO;
import com.bsuir.clean_control_server.dto.ReceiveLocationDTO;
import com.bsuir.clean_control_server.exception.ResourceNotFoundException;

import com.bsuir.clean_control_server.exception.UserNotFoundException;

import com.bsuir.clean_control_server.model.Worker;
import com.bsuir.clean_control_server.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final OrderService orderService;

    public Worker getWorkerById(Long workerId) {
        return workerRepository.findById(workerId).orElseThrow(() ->
                new ResourceNotFoundException("Worker with id: " + workerId + " doesn't exists in database"));
    }

    public ReceiveLocationDTO newLocation(ReceiveLocationDTO receiveLocationDTO){
        workerRepository.updateLocation(receiveLocationDTO.getLatitude(), receiveLocationDTO.getLongitude(), receiveLocationDTO.getPhoneNumber());
        return receiveLocationDTO;
    }

    public List<Worker> getAllWorkersByOrderId(Long orderId) {
        return workerRepository.findAllByOrder(orderService.getOrderById(orderId));
    }

    public SendLocationDTO getWorkerLocation(Long workerId) {
        Worker worker = getWorkerById(workerId);
        return new SendLocationDTO(worker.getLatitude(), worker.getLongitude());
    }
  
    public Worker findByPhoneNumber(String phoneNumber){
        return workerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new UserNotFoundException("There is no worker with phone number " + phoneNumber + " in database"));
    }

}
