package com.bsuir.clean_control_server.repository;

import com.bsuir.clean_control_server.model.Manager;
import com.bsuir.clean_control_server.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByManager(Manager manager);
    List<Order> findAllByManagerAndStartingTimeAfterAndEndingTimeBefore(Manager manager, Timestamp dateAfter, Timestamp dateBefore);
}
