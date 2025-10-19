package com.example.goatTutorats.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "oral_exam")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OralExam {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private LocalDate date;
    private Float finalGrade;
    private String comments;

    // Each defense belongs to exactly one academic year
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    @JsonBackReference
    private AcademicYear academicYear;
}
