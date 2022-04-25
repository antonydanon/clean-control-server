package com.bsuir.clean_control_server.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "workers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    private String name;

    private String surname;

    private int age;

    private double longitude;

    private double latitude;

    private Timestamp time;

    private Boolean videoStatus;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
