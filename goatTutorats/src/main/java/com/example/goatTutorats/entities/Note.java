package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "note")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "author", nullable = true)
    private String author;

    @Column(name = "comments", nullable = false)
    private String comments;

    // Each note belongs to one academic year
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @Override
    public String toString() {
        return String.format("Note{"
                        + "id=%s, "
                        + "author='%s', "
                        + "comments='%s', "
                        + "academicYear=%s}",
                id, author, comments, academicYear.getId());
    }
}
