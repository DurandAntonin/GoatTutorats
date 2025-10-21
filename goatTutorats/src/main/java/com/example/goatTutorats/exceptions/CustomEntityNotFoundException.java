package com.example.goatTutorats.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CustomEntityNotFoundException extends EntityNotFoundException {
    public CustomEntityNotFoundException(String entityId) {
        super(String.format("Entity with id '%s' not found", entityId));
    }
}