package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Entity representing a Company.
 */
@Entity
@Table(name = "company")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    /**
     * Unique identifier for the Company.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Name of the company.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Address of the company.
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Access information for the company.
     */
    @Column(name = "accessInfo", nullable = false)
    private String accessInfo;

    @Override
    public String toString() {
        return String.format("Company{"
                        + "id=%s, "
                        + "name='%s', "
                        + "address='%s', "
                        + "accessInfo='%s', ",
                id, name, address, accessInfo);
    }
}
