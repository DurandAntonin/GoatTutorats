package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "mentors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private String phone;
    private String remarks;
}
