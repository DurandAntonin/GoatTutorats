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
