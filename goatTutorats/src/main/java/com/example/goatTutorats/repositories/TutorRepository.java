package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TutorRepository extends  JpaRepository<Tutor, UUID> {
    Optional<Tutor> findByUsername(String username);
}
