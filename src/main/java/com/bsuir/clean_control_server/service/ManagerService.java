package com.bsuir.clean_control_server.service;

import com.bsuir.clean_control_server.dto.WorkDayIntervalDTO;
import com.bsuir.clean_control_server.exception.ResourceNotFoundException;

import com.bsuir.clean_control_server.model.Manager;
import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.repository.ManagerRepository;
import com.bsuir.clean_control_server.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final static int MS_IN_DAY = 86400000;
    private final static String TIME_PART = " 00:00:00";

    private final ManagerRepository managerRepository;
    private final OrderRepository orderRepository;

    public Manager getManagerByPhoneNumber(String phone) {
        return managerRepository.getManagerByPhoneNumber(phone).orElseThrow(() ->
                new ResourceNotFoundException("Manager with phone: " + phone + " doesn't exists in database"));
    }

   /**
    * This method was created on the birthday of a wonderful person 24.04.2022 00:00
    * */
    public WorkDayIntervalDTO getWorkDayInterval(String phone) {
        List<Order> orders = getTodaysOrders(phone);
        Timestamp startingTime = Timestamp.valueOf(new Date(System.currentTimeMillis()) + TIME_PART);
        Timestamp endingTime = startingTime;
        if (orders.size() >= 1) {
            startingTime = endingTime = orders.get(0).getStartingTime();
            for (Order order : orders) {
                startingTime = startingTime.after(order.getStartingTime()) ? order.getStartingTime() : startingTime;
                endingTime = endingTime.before(order.getEndingTime()) ? order.getEndingTime() : endingTime;
            }
        }
        return new WorkDayIntervalDTO(startingTime, endingTime);
    }

    private List<Order> getTodaysOrders(String phone) {
        Timestamp dateAfter = Timestamp.valueOf(new Date(System.currentTimeMillis()) + TIME_PART);
        Timestamp dateBefore = Timestamp.valueOf(new Date(System.currentTimeMillis() + MS_IN_DAY) + TIME_PART);
        return orderRepository.findAllByManagerAndStartingTimeAfterAndEndingTimeBefore(getManagerByPhoneNumber(phone), dateAfter, dateBefore);
    }
}
