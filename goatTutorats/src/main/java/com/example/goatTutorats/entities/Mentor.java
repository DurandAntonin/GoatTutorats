package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Entity representing a Mentor.
 */
@Entity
@Table(name = "mentor")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

    /**
     * Unique identifier for the Mentor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * First name of the Mentor.
     */
    @Column(name = "firstName", nullable = false)
    private String firstName;

    /**
     * Last name of the Mentor.
     */
    @Column(name = "lastName", nullable = false)
    private String lastName;

    /**
     * Position of the Mentor.
     */
    @Column(name = "position", nullable = false)
    private String position;

    /**
     * Email of the Mentor.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Phone number of the Mentor.
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * Remarks about the Mentor.
     */
    @Column(name = "remarks", nullable = false)
    private String remarks;

    @Override
    public String toString() {
        return String.format("Mentor{"
                        + "id=%s, "
                        + "firstName='%s', "
                        + "lastName='%s', "
                        + "position='%s', "
                        + "email='%s', "
                        + "phone='%s', "
                        + "remarks='%s', ",
                id, firstName, lastName,
                position, email, phone, remarks);
    }
}
