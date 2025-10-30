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

    /**
     * Unique identifier for the Academic Year.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * The Year associated with this Academic Year.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "year_id", nullable = false)
    private Year year;

    /**
     * The study level of the apprentice during this Academic Year.
     */
    @Column(name = "study_level")
    @Enumerated(EnumType.STRING)
    private StudyLevel studyLevel = StudyLevel.ING1;

    /**
     * The Apprentice associated with this Academic Year.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "apprentice_id", nullable = false)
    private Apprentice apprentice;

    /**
     * The Company where the apprentice is placed during this Academic Year.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    /**
     * The Mentor assigned to the apprentice for this Academic Year.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    /**
     * List of Missions assigned during this Academic Year.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "academic_year_id")
    private List<Mission> missions;

    /**
     * The Visit associated with this Academic Year.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Visit visit;

    /**
     * The Report Evaluation associated with this Academic Year.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ReportEvaluation reportEvaluation;

    /**
     * The Oral Exam associated with this Academic Year.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private OralExam oralExam;

    /**
     * List of Notes recorded during this Academic Year.
     */
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
