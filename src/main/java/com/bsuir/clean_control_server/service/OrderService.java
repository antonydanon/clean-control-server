package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.exception.ResourceNotFoundException;
import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ManagerService managerService;

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("Order with id: " + orderId + " doesn't exists in database"));

    }

    public List<Order> getManagerOrdersByPhone(String phone) {
        return orderRepository.findAllByManager(managerService.getManagerByPhone(phone));
    }
}
