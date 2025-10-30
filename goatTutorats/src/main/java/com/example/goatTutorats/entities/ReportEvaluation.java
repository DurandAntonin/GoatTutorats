package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Entity representing a Report Evaluation.
 */
@Entity
@Table(name = "report_evaluation")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReportEvaluation {

    /**
     * Unique identifier for the Report Evaluation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Thesis of the report.
     */
    private String thesis;
    /**
     * Topic of the report.
     */
    private String topic;
    /**
     * Final grade of the report.
     */
    private Float finalGrade;
    /**
     * Comments on the report.
     */
    private String comments;

    @Override
    public String toString() {
        return String.format("ReportEvaluation{"
                        + "id=%s, "
                        + "thesis='%s', "
                        + "topic='%s', "
                        + "finalGrade=%s, "
                        + "comments='%s', ",
                id, thesis, topic, finalGrade, comments);
    }
}
