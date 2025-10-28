package com.example.goatTutorats.entities;

import com.example.goatTutorats.enums.VisitFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private VisitFormat format;
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
