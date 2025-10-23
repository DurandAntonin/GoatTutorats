package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "mission")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "keywords", nullable = false)
    private String keywords;

    @Column(name = "targetJob", nullable = false)
    private String targetJob;

    @Column(name = "comments", nullable = false)
    private String comments;

    // Each mission belongs to one academic year
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @Override
    public String toString() {
        return String.format("Mission{"
                        + "id=%s, "
                        + "keywords='%s', "
                        + "targetJob='%s', "
                        + "comments='%s', "
                        + "academicYear=%s}",
                id, keywords, targetJob, comments, academicYear.getId());
    }
}
