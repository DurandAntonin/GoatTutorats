package com.example.goatTutorats.controlers;

import com.example.goatTutorats.dtos.ApprenticeUpdateDTO;
import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.services.ApprenticeService;
import com.example.goatTutorats.services.TutorService;
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

    private final TutorService tutorService;

    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService,  TutorService tutorService) {
        this.apprenticeService = apprenticeService;
        this.tutorService = tutorService;
    }

    @GetMapping("/get-dashboard")
    public String dashboard(Principal principal, Authentication authentication, Model model) {
        // retrieve connected tutor information
        String userName = principal.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);
        model.addAttribute("authorities", authorities);

        // retrieve all apprentices for this tutor
        Tutor tutor = this.tutorService.getTutorByUsername(userName);

        List<ApprenticeRecordDTO> apprentices = apprenticeService.getApprenticesByTutorForThisYear(tutor.getId());
        model.addAttribute("apprentices", apprentices);

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
        return "redirect:/apprentice/get-apprentice/";
    }

    @PatchMapping("/{id}")
    public Apprentice updateApprentice(@PathVariable("id") UUID id,
                                       @RequestBody ApprenticeUpdateDTO dto) {
        return apprenticeService.updateApprentice(id, dto);
    }


    @PatchMapping("updateApprentice/{id}")
    public String updateApprenticeT(@PathVariable("id") UUID id, @ModelAttribute Apprentice apprentice)
    {
        return "apprentice";
    }

    @PostMapping("createApprentice")
    public String createApprentice(@ModelAttribute Apprentice apprentice)
    {
        return "apprentice";
    }
}



