package com.example.goatTutorats.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String programme;
    private String majeure;

    // Each apprentice is assigned to one tutor
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tutor_id", nullable = false)
    @JsonBackReference
    private Tutor tutor;

    // One apprentice can have multiple academic years
    @OneToMany(mappedBy = "apprentice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AcademicYear> academicYears;

    @Override
    public String toString() {
        return "";
    }
}
