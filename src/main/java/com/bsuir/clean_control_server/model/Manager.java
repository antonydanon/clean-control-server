package com.bsuir.clean_control_server.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "managers")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    private String name;

    private String surname;
}
