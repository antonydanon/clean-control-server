package com.bsuir.clean_control_server.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    private String description;

    private double radius;

    private double longitude;

    private double latitude;

    @Column(name = "starting_time")
    private Timestamp startingTime;

    @Column(name = "ending_time")
    private Timestamp endingTime;

}
