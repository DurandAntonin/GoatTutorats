package com.example.goatTutorats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tutors")
@Data
@AllArgsConstructor
public class Tutor implements UserDetails {

    private final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String username;
    private String password;

    // One tutor can have multiple apprentices
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Apprentice> apprentices;

    public Tutor() {
        this.grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
