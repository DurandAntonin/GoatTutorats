package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entity representing an Oral Exam.
 */
@Entity
@Table(name = "oral_exam")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OralExam {

    /**
     * Unique identifier for the Oral Exam.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Date of the oral exam.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    /**
     * Final grade received in the oral exam.
     */
    private Float finalGrade;
    /**
     * Comments or feedback about the oral exam.
     */
    private String comments;

    @Override
    public String toString() {
        return String.format("Defense{"
                        + "id=%s, "
                        + "date=%s, "
                        + "finalGrade=%s, "
                        + "comments='%s', ",
                id, date, finalGrade, comments);
    }
}
