package com.example.goatTutorats.entities;

import com.example.goatTutorats.enums.VisitFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entity representing a Visit.
 */
@Entity
@Table(name = "visit")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    /**
     * Unique identifier for the Visit.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Date of the visit.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    /**
     * Format of the visit (e.g., IN_PERSON, VIRTUAL).
     */
    private VisitFormat format;

    /**
     * Comments or notes about the visit.
     */
    private String comments;

    @Override
    public String toString() {
        return String.format("Visit{"
                        + "id=%s, "
                        + "date=%s, "
                        + "format='%s', "
                        + "comments='%s', ",
                id, date, format, comments);
    }
}
