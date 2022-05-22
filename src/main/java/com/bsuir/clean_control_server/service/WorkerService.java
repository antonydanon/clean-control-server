package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.*;
import com.bsuir.clean_control_server.exception.ResourceNotFoundException;
import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.model.Worker;
import com.bsuir.clean_control_server.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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

    public Worker getWorkerByPhoneNumber(String phoneNumber) {
        return workerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new ResourceNotFoundException("There is no worker with phone number " + phoneNumber + " in database"));
    }

    public StartBroadcastDTO updateWorkerLocation(ReceiveLocationDTO receiveLocationDTO) {
        workerRepository.updateLocation(receiveLocationDTO.getLatitude(), receiveLocationDTO.getLongitude(), receiveLocationDTO.getPhoneNumber());
        return new StartBroadcastDTO(getWorkerByPhoneNumber(receiveLocationDTO.getPhoneNumber()).isShowingBroadcast());
    }

    public List<WorkerDTO> getListOfWorkersDTO(Long orderId) {
        return workerRepository.findAllByOrder(orderService.getOrderById(orderId)).stream()
                .map(worker -> new WorkerDTO(worker, isWorkerAtWork(worker, worker.getOrder())))
                .toList();
    }

    public SendLocationDTO getWorkerLocation(Long workerId) {
        Worker worker = getWorkerById(workerId);
        return new SendLocationDTO(worker.getLatitude(), worker.getLongitude(),
                isWorkerAtWork(worker, worker.getOrder()));
    }

    private boolean isWorkerAtWork(Worker worker, Order order) {
        double curRadius = EARTH_RADIUS * sqrt(2)
                * sqrt(1 - cos(RADIANS * worker.getLatitude()) * cos(RADIANS * order.getLatitude())
                * cos(RADIANS * (worker.getLongitude() - order.getLongitude()))
                - sin(RADIANS * worker.getLatitude()) * sin(RADIANS * order.getLatitude()));
        return curRadius <= order.getRadius();
    }

    public List<QuittersDTO> getQuitters(String phone) {
        return orderService.getOrdersByPhone(phone).stream()
                .filter(this::isOrderInProgress)
                .flatMap(order -> getQuittersInOrder(order).stream())
                .toList();
    }

    private List<QuittersDTO> getQuittersInOrder(Order order) {
        return workerRepository.findAllByOrder(order).stream()
                .filter(worker -> !isWorkerAtWork(worker, order))
                .map(quitter -> new QuittersDTO(
                        quitter.getName(),
                        quitter.getSurname(),
                        quitter.getPhoneNumber(),
                        order.getId())).toList();
    }

    private boolean isOrderInProgress(Order order) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return !order.getStartingTime().after(now) && !order.getEndingTime().before(now);
    }

    public ShowingBroadcastDTO updateShowingBroadcast(ShowingBroadcastDTO showingBroadcastDTO) {
        workerRepository.updateShowingBroadcast(showingBroadcastDTO.getShowingBroadcast(), showingBroadcastDTO.getWorkerId());
        return showingBroadcastDTO;
    }
}
