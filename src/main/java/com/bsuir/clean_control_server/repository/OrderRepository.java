package com.bsuir.clean_control_server.repository;

import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
