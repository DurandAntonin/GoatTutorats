package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "apprentice")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apprentice {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id; // UUID generated natively by Hibernate 6.5+

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String programme;
    private String majeure;

    // Each apprentice is assigned to one tutor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    // One apprentice can have multiple academic years
    @OneToMany(mappedBy = "apprentice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcademicYear> academicYears;
}
