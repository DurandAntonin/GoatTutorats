package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "report_evaluation")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReportEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String thesis;
    private String topic;
    private Float finalGrade;
    private String comments;

    // Each school evaluation is submitted for one academic year
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @Override
    public String toString() {
        return String.format("ReportEvaluation{"
                        + "id=%s, "
                        + "thesis='%s', "
                        + "topic='%s', "
                        + "finalGrade=%s, "
                        + "comments='%s', "
                        + "academicYear=%s}",
                id, thesis, topic, finalGrade, comments, academicYear.getId());
    }
}
