package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "school_evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolEvaluation {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String thesis;
    private String topic;
    private Float finalGrade;
    private String comments;

    // Each school evaluation is submitted for one academic year
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;
}
