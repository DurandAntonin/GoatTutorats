package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents a user entity in the system.
 * It implements the UserDetails interface from Spring Security
 * to provide user authentication and authorization details.
 */
@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements UserDetails {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Username of the user, must be unique.
     */
    @Column(unique = true)
    private String username;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Roles assigned to the user for authorization purposes.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<UserRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getRoleName()))
                .toList();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        String roleIds = roles.stream()
                .map(role -> role.getId().toString())
                .collect(Collectors.joining(", "));

        return String.format("User{"
                        + "id=%s, "
                        + "username='%s', "
                        + "password='%s', "
                        + "roles=[%s]}",
                id, username, password, roleIds);
    }
}
