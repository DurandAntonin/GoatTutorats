package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing Tutor entities.
 */
public interface TutorRepository extends  JpaRepository<Tutor, UUID> {
    /**
     * Find a Tutor entity by its username.
     * @param username the username of the tutor to find
     * @return an Optional containing the Tutor entity if found, otherwise an empty Optional
     */
    Optional<Tutor> findByUsername(String username);
}
