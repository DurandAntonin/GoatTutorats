package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "company")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "accessInfo", nullable = false)
    private String accessInfo;

    @OneToOne(mappedBy = "company")
    private Mentor mentor;

    @Override
    public String toString() {
        return String.format("Company{"
                        + "id=%s, "
                        + "name='%s', "
                        + "address='%s', "
                        + "accessInfo='%s', "
                        + "mentor=%s}",
                id, name, address, accessInfo, mentor.getId());
    }
}
