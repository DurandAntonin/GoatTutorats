package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

/**
 * Entity representing an Apprentice.
 */
@Entity
@Table(name = "apprentice")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Apprentice {

    /**
     * Unique identifier for the Apprentice.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * First name of the Apprentice.
     */
    @Column(name = "firstName", nullable = false)
    private String firstName;

    /**
     * Last name of the Apprentice.
     */
    @Column(name = "lastName", nullable = false)
    private String lastName;

    /**
     * Email of the Apprentice.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Phone number of the Apprentice.
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * Program of the Apprentice.
     */
    @Column(name = "program", nullable = false)
    private String program;

    /**
     * Major of the Apprentice.
     */
    @Column(name = "major", nullable = false)
    private String major;

    /**
     * Indicates whether the Apprentice is archived.
     */
    @Column(name = "archived", nullable = false)
    @ColumnDefault("false")
    private boolean archived;

    /**
     * The Tutor associated with the Apprentice.
     * Each Apprentice is linked to one Tutor.
     */
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
