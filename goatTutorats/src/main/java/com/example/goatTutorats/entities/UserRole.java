package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "userRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

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
