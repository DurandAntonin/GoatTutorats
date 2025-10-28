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

    @Column(name = "author", nullable = false)
    private String author;

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
