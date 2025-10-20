package com.example.goatTutorats.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "mentor")
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

    // Each tutor works at exactly one company
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Company company;
}
