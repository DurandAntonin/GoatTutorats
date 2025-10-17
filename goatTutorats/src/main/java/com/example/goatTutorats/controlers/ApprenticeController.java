package com.example.goatTutorats.controlers;

import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.services.ApprenticeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

@Controller
@RequestMapping("apprentice")
public class ApprenticeController {

    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @GetMapping("/get-dashboard")
    public String dashboard(Principal principal, Authentication authentication, Model model) {
        // retrieve connected tutor information
        String userName = principal.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);
        model.addAttribute("authorities", authorities);

        return "dashboard";
    }

    @GetMapping("/get-apprentice/{id}")
    public String apprentice(@PathVariable UUID id, Principal principal, Authentication authentication, Model model){
        // retrieve connected tutor information
        String userName = principal.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);
        model.addAttribute("authorities", authorities);

        model.addAttribute("apprentice", new Apprentice());
        return "apprentice";
    }
}
