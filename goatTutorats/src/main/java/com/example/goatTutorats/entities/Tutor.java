package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
@Table(name = "tutor")
public class Tutor extends User {

    // One tutor can have multiple apprentices
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Apprentice> apprentices;
}
