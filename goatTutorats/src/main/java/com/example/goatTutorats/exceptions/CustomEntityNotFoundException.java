package com.example.goatTutorats.exceptions;

import jakarta.persistence.EntityNotFoundException;

/**
 * Custom exception thrown when an entity is not found.
 */
public class CustomEntityNotFoundException extends EntityNotFoundException {
    /**
     * Constructs a new CustomEntityNotFoundException with a detailed message.
     * @param entityId the ID of the entity that was not found
     */
    public CustomEntityNotFoundException(String entityId) {
        super(String.format("Entity with id '%s' not found", entityId));
    }
}