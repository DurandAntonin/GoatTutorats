package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    // Each apprentice is assigned to one tutor
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    // One apprentice can have multiple academic years
    @OneToMany(mappedBy = "apprentice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcademicYear> academicYears;

    @Override
    public String toString() {
        String academicYearIds = academicYears == null ? "":  academicYears.stream()
                .map(academicYear -> academicYear.getId().toString())
                .collect(Collectors.joining(", "));

        return String.format("Apprentice{"
                        + "id=%s, "
                        + "firstName='%s', "
                        + "lastName='%s', "
                        + "email='%s', "
                        + "phone='%s', "
                        + "program='%s', "
                        + "major='%s', "
                        + "tutor=%s, "
                        + "academicYears=[%s]}",
                id, firstName, lastName, email, phone, program, major, tutor == null ? "" : tutor.getId(), academicYearIds);
    }
}
