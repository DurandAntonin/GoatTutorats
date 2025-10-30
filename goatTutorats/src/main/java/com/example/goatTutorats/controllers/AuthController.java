package com.example.goatTutorats.controllers;

import com.example.goatTutorats.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Controller for authentication-related endpoints.
 */
@Controller
@RequestMapping("auth")
public class AuthController {


    /**
     * Retrieve login page.
     * @param principal Current logged in user
     * @param model Model to store information for html template
     * @return Login page
     */
    @GetMapping("/get-login-page")
    public String getLoginPage(Principal principal, Model model) {
        // redirect user if connected
        if (principal != null){
            return "redirect:/apprentice/get-dashboard";
        }

        // create empty object to store user credentials
        User user = new User();
        model.addAttribute("user", user);

        return "login";
    }
}
