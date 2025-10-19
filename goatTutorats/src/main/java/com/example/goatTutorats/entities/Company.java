package com.example.goatTutorats.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String address;
    private String accessInfo;

    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Mentor mentor;
}
