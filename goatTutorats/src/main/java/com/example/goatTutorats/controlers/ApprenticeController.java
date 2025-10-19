package com.example.goatTutorats.controlers;

import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.services.ApprenticeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.UUID;
import java.util.List;

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

    @GetMapping("/get-apprentice-research")
    public String apprenticeResearch(Principal principal, Authentication authentication, Model model) {
        // retrieve connected tutor information
        String userName = principal.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);
        model.addAttribute("authorities", authorities);

        return "apprentice-research";
    }

    @GetMapping("/get-apprentice/{id}")
    public String getApprenticeInformation(@PathVariable UUID id, Principal principal, Authentication authentication, Model model) {
        // retrieve connected tutor information
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // store those information in model to access in html templates
        model.addAttribute("username", principal.getName());
        model.addAttribute("authorities", authorities);

        // store form name, action and method
        model.addAttribute("formName", "nom apprenti");
        model.addAttribute("formAction", "/apprentice/updateApprentice/" + id);
        model.addAttribute("formMethod", "PATCH");

        model.addAttribute("apprentice", new Apprentice());
        return "apprentice";
    }

    @GetMapping("/get-apprentice-creation")
    public String getApprenticeCreationPage(Principal principal, Authentication authentication, Model model) {
        // retrieve connected tutor information
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // store those information in model to access in html templates
        model.addAttribute("username", principal.getName());
        model.addAttribute("authorities", authorities);

        // store form name, action and method
        model.addAttribute("formName", "Ajouter un apprenti");
        model.addAttribute("formAction", "/apprentice/createApprentice");
        model.addAttribute("formMethod", "POST");

        model.addAttribute("apprentice", new Apprentice());
        return "apprentice";
    }

    @GetMapping("getApprenticeByTutorAndCurrentYear/{idTutor}")
    public List<ApprenticeRecordDTO> getApprenticeByTutorAndCurrentYear(@PathVariable("idTutor") UUID idT)
    {
        return apprenticeService.getApprenticesByTutorForThisYear(idT);
    }

    @PatchMapping("updateApprentice/{id}")
    public String updateApprentice(@PathVariable("id") UUID id, @ModelAttribute Apprentice apprentice)
    {
        return "apprentice";
    }

    @PostMapping("createApprentice")
    public String createApprentice(@ModelAttribute Apprentice apprentice)
    {
        return "apprentice";
    }
}



