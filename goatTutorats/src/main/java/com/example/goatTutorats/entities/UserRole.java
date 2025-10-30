package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Entity representing a User Role.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "userRole")
public class UserRole {

    /**
     * Unique identifier for the UserRole.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Name of the role.
     */
    @Column(name = "roleName", nullable = false, unique = true)
    private String roleName;

    @ManyToMany
    Set<User> users;

    @Override
    public String toString() {
        String userIds = users.stream()
                .map(user -> user.getId().toString())
                .collect(Collectors.joining(", "));

        return String.format("UserRole{"
                        + "id=%s, "
                        + "roleName='%s', "
                        + "users=[%s]}",
                id, roleName, userIds);
    }
}
