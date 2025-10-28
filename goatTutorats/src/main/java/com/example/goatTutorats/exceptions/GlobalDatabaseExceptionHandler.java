package com.example.goatTutorats.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDatabaseExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("errorMessage", "Erreur d’intégrité des données : " + ex.getMostSpecificCause().getMessage());
        return "errors/default-error";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFound(EntityNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Donnée introuvable en base : " + ex.getMessage());
        return "errors/default-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model) {
        if (ex.getMessage().contains("No static resource")) return "errors/error-404";

        model.addAttribute("errorMessage", "Erreur inconnue : " + ex.getMessage());
        return "errors/default-error";
    }
}
