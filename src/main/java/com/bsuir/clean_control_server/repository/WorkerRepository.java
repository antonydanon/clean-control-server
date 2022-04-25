package com.bsuir.clean_control_server.repository;

import com.bsuir.clean_control_server.model.Order;
import com.bsuir.clean_control_server.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Modifying
    @Query("update Worker w set w.latitude = ?1, w.longitude = ?2 where w.phoneNumber = ?3")
    void updateLocation(double latitude, double longitude, String phoneNumber);

    @Modifying
    @Query("update Worker w set w.showingBroadcast = ?1 where w.id = ?2")
    void updateShowingBroadcast(boolean showingBroadcast, Long workerId);

    List<Worker> findAllByOrder(Order order);
    Optional<Worker> findByPhoneNumber(String phoneNumber);
}
