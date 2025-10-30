package com.example.goatTutorats.entities;

import com.example.goatTutorats.enums.StudyLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This entity represents an Academic Year for an apprentice.
 */
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "year_id", nullable = false)
    private Year year;

    @Column(name = "study_level")
    @Enumerated(EnumType.STRING)
    private StudyLevel studyLevel = StudyLevel.ING1;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "apprentice_id", nullable = false)
    private Apprentice apprentice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "academic_year_id")
    private List<Mission> missions;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Visit visit;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ReportEvaluation reportEvaluation;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private OralExam oralExam;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "academic_year_id")
    private List<Note> notes;

    /**
     * Default constructor initializing lists and associated entities.
     */
    public AcademicYear() {
        this.missions = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.visit = new Visit();
        this.reportEvaluation = new ReportEvaluation();
        this.oralExam = new OralExam();
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
                id, year.toString(), apprentice.getId(), company.getId(),
                mentor.getId(), missionIds, visit.getId(),
                reportEvaluation.getId(), oralExam.getId(),
                noteIds);
    }
}
