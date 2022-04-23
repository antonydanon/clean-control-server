package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.QuittersDTO;
import com.bsuir.clean_control_server.dto.ReceiveLocationDTO;
import com.bsuir.clean_control_server.dto.SendLocationDTO;
import com.bsuir.clean_control_server.exception.ResourceNotFoundException;
import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.model.Worker;
import com.bsuir.clean_control_server.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        return new SendLocationDTO(worker.getLatitude(), worker.getLongitude(),
                                   isWorkerAtWork(worker, worker.getOrder()));
    }

    private boolean isWorkerAtWork(Worker worker, Order order){
        double curRadius = EARTH_RADIUS * sqrt(2)
                * sqrt(1 - cos(RADIANS * worker.getLatitude()) * cos(RADIANS * order.getLatitude())
                * cos(RADIANS * (worker.getLongitude() - order.getLongitude()))
                - sin(RADIANS * worker.getLatitude()) * sin(RADIANS * order.getLatitude()));
        return curRadius <= order.getRadius();
    }

    public List<QuittersDTO> getQuitters(String phone) {
        List<QuittersDTO> quitters = new ArrayList<>();
        for (Order order: orderService.getOrdersByPhone(phone)) {
            quitters.addAll(getQuittersInOrder(order));
        }
        return quitters;
    }

    public List<QuittersDTO> getQuittersInOrder(Order order) {
        List<QuittersDTO> quittersInOrder = new ArrayList<>();
        for (Worker worker: workerRepository.findAllByOrder(order)) {
            if (!isWorkerAtWork(worker, order))
                quittersInOrder.add(
                        new QuittersDTO(worker.getName(), worker.getSurname(),
                                worker.getPhoneNumber(), order.getId())
                );
        }
        return quittersInOrder;
    }
}
