package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
@Table(name = "tutor")
public class Tutor extends User {

    // One tutor can have multiple apprentices
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Apprentice> apprentices;

    @Override
    public String toString() {
        String apprenticeIds = apprentices.stream()
                .map(apprentice -> apprentice.getId().toString())
                .collect(Collectors.joining(", "));

        return String.format("Tutor{"
                        + "user=%s, "
                        + "apprentices=[%s]}",
                super.toString(), apprenticeIds);
    }
}
