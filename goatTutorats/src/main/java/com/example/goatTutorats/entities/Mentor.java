package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "mentor")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

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
