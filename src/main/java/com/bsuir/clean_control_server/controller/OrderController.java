package com.bsuir.clean_control_server.controller;

import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getManagerOrders(@RequestParam String phone) {
        return ResponseEntity.ok().body(orderService.getOrdersByPhone(phone));
    }
}
