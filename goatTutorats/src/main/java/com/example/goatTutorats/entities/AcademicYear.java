package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "academic_year")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class AcademicYear {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private LocalDate year;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "apprentice_id", nullable = false)
    private Apprentice apprentice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @OneToMany(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mission> missions;

    @OneToOne(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    private Visit visit;

    @OneToOne(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReportEvaluation reportEvaluation;

    @OneToOne(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    private OralExam oralExam;

    @OneToMany(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;

    public AcademicYear() {
        //this.id = UUID.randomUUID();
        this.notes = new ArrayList<>();
        this.year = LocalDate.now();
        this.missions = new ArrayList<>();
        this.notes = new ArrayList<>();
    }

    @Override
    public String toString() {
        String missionIds = missions.stream()
                .map(mission -> mission.getId().toString())
                .collect(Collectors.joining(", "));

        String noteIds = notes.stream()
                .map(note -> note.getId().toString())
                .collect(Collectors.joining(", "));

        return String.format("AcademicYear{"
                        + "id=%s, "
                        + "year=%s, "
                        + "apprentice=%s, "
                        + "company=%s, "
                        + "mentor=%s, "
                        + "missions=[%s], "
                        + "visit=%s, "
                        + "reportEvaluation=%s, "
                        + "oralExam=%s, "
                        + "notes=[%s]}",
                id, year, apprentice.getId(), company.getId(),
                mentor.getId(), missionIds, visit.getId(),
                reportEvaluation.getId(), oralExam.getId(),
                noteIds);
    }
}
