package com.example.goatTutorats.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for database-related exceptions.
 */
@ControllerAdvice
public class GlobalDatabaseExceptionHandler {

    /**
     * Handle DataIntegrityViolationException.
     * @param ex the exception
     * @param model the model
     * @return the error view
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("errorMessage", "Erreur d’intégrité des données : " + ex.getMostSpecificCause().getMessage());
        return "errors/default-error";
    }

    /**
     * Handle EntityNotFoundException.
     * @param ex the exception
     * @param model the model
     * @return the error view
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFound(EntityNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Donnée introuvable en base : " + ex.getMessage());
        return "errors/default-error";
    }

    /**
     * Handle CustomEntityNotFoundException.
     * @param ex the exception
     * @param model the model
     * @return the error view
     */
    @ExceptionHandler(CustomEntityNotFoundException.class)
    public String handleCustomEntityNotFound(CustomEntityNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Donnée introuvable en base : " + ex.getMessage());
        return "errors/default-error";
    }

    /**
     * Handle generic Exception.
     * @param ex the exception
     * @param model the model
     * @return the error view
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model) {
        if (ex.getMessage().contains("No static resource")) return "errors/error-404";

        model.addAttribute("errorMessage", "Erreur inconnue : " + ex.getMessage());
        return "errors/default-error";
    }
}
