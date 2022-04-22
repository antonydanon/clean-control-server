package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.ReceiveLocationDTO;
import com.bsuir.clean_control_server.dto.SendLocationDTO;
import com.bsuir.clean_control_server.exception.ResourceNotFoundException;
import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.model.Worker;
import com.bsuir.clean_control_server.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Math.*;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerService {
    private final static double RADIANS = PI / 180;
    private final static int EARTH_RADIUS = 6378000;

    private final WorkerRepository workerRepository;
    private final OrderService orderService;

    public Worker getWorkerById(Long workerId) {
        return workerRepository.findById(workerId).orElseThrow(() ->
                new ResourceNotFoundException("Worker with id: " + workerId + " doesn't exists in database"));
    }

    public Worker getWorkerByPhoneNumber(String phoneNumber){
        return workerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new ResourceNotFoundException("There is no worker with phone number " + phoneNumber + " in database"));
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
        Order order = worker.getOrder();
        boolean isInsideZone = isWorkerAtWork(worker.getLatitude(), worker.getLongitude(),
                                              order.getLatitude(),order.getLongitude(),order.getRadius());
        return new SendLocationDTO(worker.getLatitude(), worker.getLongitude(), isInsideZone);
    }

    private boolean isWorkerAtWork(double workerLat, double workerLong,
                                   double orderLat, double orderLong, double radius ){
        double curRadius = EARTH_RADIUS * sqrt(2) * sqrt(1 - cos(RADIANS * workerLat) * cos(RADIANS * orderLat)
                * cos(RADIANS * (workerLong - orderLong)) - sin(RADIANS * workerLat) * sin(RADIANS * orderLat));
        return curRadius <= radius;
    }
}
