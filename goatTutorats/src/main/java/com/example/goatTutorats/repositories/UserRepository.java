package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing User entities.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a User entity by its username.
     * @param username the username of the user to find
     * @return an Optional containing the User entity if found, otherwise an empty Optional
     */
    Optional<User> findUserByUsername(String username);
}
