package com.bsuir.clean_control_server.repository;

import com.bsuir.clean_control_server.model.Manager;
import com.bsuir.clean_control_server.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByPhoneNumber(String phoneNumber);
}
