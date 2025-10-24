package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Table(name = "apprentice")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Apprentice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "program", nullable = false)
    private String program;

    @Column(name = "major", nullable = false)
    private String major;

    @Column(name = "archived", nullable = false)
    @ColumnDefault("false")
    private boolean archived;

    // Each apprentice is assigned to one tutor
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @Override
    public String toString() {
        return String.format("Apprentice{"
                        + "id=%s, "
                        + "firstName='%s', "
                        + "lastName='%s', "
                        + "email='%s', "
                        + "phone='%s', "
                        + "program='%s', "
                        + "major='%s', "
                        + "tutor=%s, ",
                id, firstName, lastName, email, phone, program, major, tutor.getId());
    }
}
