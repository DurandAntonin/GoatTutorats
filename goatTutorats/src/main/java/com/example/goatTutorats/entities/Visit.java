package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "visit")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private LocalDate date;
    private String format;
    private String comments;

    // Each visit belongs to one academic year
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @Override
    public String toString() {
        return String.format("Visit{"
                        + "id=%s, "
                        + "date=%s, "
                        + "format='%s', "
                        + "comments='%s', "
                        + "academicYear=%s}",
                id, date, format, comments, academicYear.getId());
    }
}
