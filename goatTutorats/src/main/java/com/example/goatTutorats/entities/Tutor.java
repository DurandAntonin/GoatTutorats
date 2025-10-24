package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
@Table(name = "tutor")
public class Tutor extends User {

    @Override
    public String toString() {
        return String.format("Tutor{"
                        + "user=%s, ",
                super.toString());
    }
}
