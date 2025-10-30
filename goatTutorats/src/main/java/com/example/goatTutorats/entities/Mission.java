package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Entity representing a Mission.
 */
@Entity
@Table(name = "mission")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    /**
     * Unique identifier for the Mission.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Keywords associated with the mission.
     */
    @Column(name = "keywords", nullable = false)
    private String keywords;

    /**
     * Target job for the mission.
     */
    @Column(name = "targetJob", nullable = false)
    private String targetJob;

    /**
     * Comments about the mission.
     */
    @Column(name = "comments", nullable = false)
    private String comments;

    @Override
    public String toString() {
        return String.format("Mission{"
                        + "id=%s, "
                        + "keywords='%s', "
                        + "targetJob='%s', "
                        + "comments='%s', ",
                id, keywords, targetJob, comments);
    }
}
