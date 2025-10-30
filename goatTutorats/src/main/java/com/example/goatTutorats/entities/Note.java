package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Entity representing a Note.
 */
@Entity
@Table(name = "note")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    /**
     * Unique identifier for the Note.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Author of the note.
     */
    @Column(name = "author", nullable = false)
    private String author;

    /**
     * Comments or content of the note.
     */
    @Column(name = "comments", nullable = false)
    private String comments;

    @Override
    public String toString() {
        return String.format("Note{"
                        + "id=%s, "
                        + "author='%s', "
                        + "comments='%s', ",
                id, author, comments);
    }
}
