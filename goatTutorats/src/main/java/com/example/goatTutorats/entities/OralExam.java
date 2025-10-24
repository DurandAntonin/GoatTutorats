package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "oral_exam")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OralExam {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private LocalDate date;
    private Float finalGrade;
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
