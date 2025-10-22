package com.example.goatTutorats.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "academic_year")
@Data
@AllArgsConstructor
public class AcademicYear {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private LocalDate year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apprentice_id", nullable = false)
    @JsonBackReference
    private Apprentice apprentice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonManagedReference
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    @JsonManagedReference
    private Mentor mentor;

    @OneToMany(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Mission> missions;

    @OneToOne(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Visit visit;

    @OneToOne(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private ReportEvaluation reportEvaluation;

    @OneToOne(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private OralExam oralExam;

    @OneToMany(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Note> notes;

    public AcademicYear() {
        this.notes = new ArrayList<>();
        this.setMissions(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "";
    }
}
