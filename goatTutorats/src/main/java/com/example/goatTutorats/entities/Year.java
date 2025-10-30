package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entity representing a Year.
 */
@Entity
@Table(name = "year")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Year {

    /**
     * Unique identifier for the Year.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * The year represented as a LocalDate.
     */
    private LocalDate year;

    @Override
    public String toString() {
        return String.format("Year{"
                        + "id=%s, "
                        + "year=%s}",
                id, year);
    }
}
