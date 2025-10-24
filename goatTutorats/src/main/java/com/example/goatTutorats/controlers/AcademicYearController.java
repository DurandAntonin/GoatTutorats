package com.example.goatTutorats.controlers;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.services.AcademicYearService;
import com.example.goatTutorats.services.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

@Controller
@RequestMapping("academicYear")
public class AcademicYearController {

    private final AcademicYearService academicYearService;
    private final TutorService tutorService;

    public AcademicYearController(AcademicYearService academicYearService,  TutorService tutorService) {
        this.academicYearService = academicYearService;
        this.tutorService = tutorService;
    }

    @GetMapping("/get-apprentice-academic-year/{id}")
    public String getApprenticeAcademicYearInformation(@PathVariable UUID id, Principal principal, Authentication authentication, Model model) {
        // retrieve connected tutor information
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // store those information in model to access in html templates
        model.addAttribute("username", principal.getName());
        model.addAttribute("authorities", authorities);

        // retrieve academic year information for this apprentice
        AcademicYear apprenticeAcademicYear;
        try{
            apprenticeAcademicYear = this.academicYearService.findById(id);
        }
        catch (CustomEntityNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
        model.addAttribute("apprenticeAcademicYear", apprenticeAcademicYear);

        // store form name, action and method
        model.addAttribute("formName", apprenticeAcademicYear.getApprentice().getFirstName() +" "+ apprenticeAcademicYear.getApprentice().getLastName());
        model.addAttribute("formAction", "/academicYear/update-apprentice-academic-year/" + id);
        model.addAttribute("formMethod", "PATCH");
        model.addAttribute("submitFormButtonName", "Mettre à jour");

        return "apprentice";
    }

    @GetMapping("/get-apprentice-academic-year-creation")
    public String getApprenticeAcademicYearCreationPage(Principal principal, Authentication authentication, Model model) {
        // retrieve connected tutor information
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // store those information in model to access in html templates
        model.addAttribute("username", principal.getName());
        model.addAttribute("authorities", authorities);

        // store form name, action and method
        model.addAttribute("formName", "Ajouter un apprenti");
        model.addAttribute("formAction", "/academicYear/create-apprentice-academic-year");
        model.addAttribute("formMethod", "POST");
        model.addAttribute("submitFormButtonName", "Ajouter");

        // create empty apprentice academic year
        model.addAttribute("apprenticeAcademicYear", new AcademicYear());
        return "apprentice";
    }

    @PatchMapping("/update-apprentice-academic-year/{id}")
    public String updateApprenticeAcademicYear(@PathVariable UUID id, @ModelAttribute AcademicYear apprenticeAcademicYear)
    {
        try{
            this.academicYearService.modifyAcademicYear(id,apprenticeAcademicYear);
        }
        catch (CustomEntityNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        return "redirect:/academicYear/get-apprentice-academic-year/" + id;
    }

    @PostMapping("/create-apprentice-academic-year")
    public String createApprenticeAcademicYear(@ModelAttribute AcademicYear apprenticeAcademicYear, Principal principal)
    {
        // retrieve current tutor of future apprentice
        Tutor tutor;
        try{
            tutor = this.tutorService.getTutorByUsername(principal.getName());
        }
        catch (CustomEntityNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        // create new apprentice and return it
        AcademicYear newApprenticeAcademicYear = this.academicYearService.addApprenticeAcademicYear(apprenticeAcademicYear, tutor);

        return "redirect:/academicYear/get-apprentice-academic-year/" + newApprenticeAcademicYear.getId();
    }

    @PostMapping("/create-academic-year")
    public String createAcademicYear()
    {
        return "redirect:/apprentice/get-dashboard";
    }
}
