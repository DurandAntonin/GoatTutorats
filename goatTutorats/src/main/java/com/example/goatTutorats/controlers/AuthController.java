package com.example.goatTutorats.controlers;

import com.example.goatTutorats.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("auth")
public class AuthController {


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
