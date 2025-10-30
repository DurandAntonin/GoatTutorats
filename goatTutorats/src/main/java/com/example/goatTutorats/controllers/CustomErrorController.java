package com.example.goatTutorats.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Custom error controller to handle application errors.
 */
@Controller
@RequestMapping("error")
public class CustomErrorController implements ErrorController {

    /**
     * Handle errors and display a custom error page.
     * @param request HttpServletRequest to retrieve error details
     * @param model Model to store information for html template
     * @return Custom error page
     */
    @RequestMapping("/")
    public String handleError(HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

        return "errors/default-error";
    }
}