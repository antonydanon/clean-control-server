package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.WorkDayIntervalDTO;
import com.bsuir.clean_control_server.exception.ResourceNotFoundException;
import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.model.Worker;
import com.bsuir.clean_control_server.repository.OrderRepository;
import com.bsuir.clean_control_server.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WorkerRepository workerRepository;
    private final ManagerService managerService;

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("Order with id: " + orderId + " doesn't exists in database"));

    }

    public List<Order> getOrdersByPhone(String phone) {
        return orderRepository.findAllByManager(managerService.getManagerByPhoneNumber(phone));
    }

    public WorkDayIntervalDTO getOrderWorkDay(String workerPhone) {
        Worker worker = workerRepository.findByPhoneNumber(workerPhone).orElseThrow(() ->
                new ResourceNotFoundException("There is no worker with phone number " + workerPhone + " in database"));
        Order order = worker.getOrder();
        return new WorkDayIntervalDTO(order.getStartingTime(), order.getEndingTime());
    }
}
