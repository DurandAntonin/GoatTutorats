package com.example.goatTutorats.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class CustomErrorController implements ErrorController {

    @RequestMapping("/")
    public String handleError(HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

        return "errors/default-error";
    }
}