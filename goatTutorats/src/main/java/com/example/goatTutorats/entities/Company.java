package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String accessInfo;

    // Optional: if you want bi-directional, you can map back to mentor
    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
    private Mentor mentor;
}
