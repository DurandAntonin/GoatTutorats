package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;


/**
 * Entity representing a Tutor, which is a specialized type of User.
 * This entity extends the User class and is mapped to the "tutor" table in the database.
 */
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
