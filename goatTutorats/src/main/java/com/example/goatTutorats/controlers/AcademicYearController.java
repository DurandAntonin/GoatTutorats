package com.example.goatTutorats.controlers;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.services.AcademicYearService;
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

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
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
        System.out.println(apprenticeAcademicYear);
        System.out.println(apprenticeAcademicYear.getApprentice());
        System.out.println(apprenticeAcademicYear.getNotes());
        System.out.println(apprenticeAcademicYear.getMentor());
        System.out.println(apprenticeAcademicYear.getCompany());
        System.out.println(apprenticeAcademicYear.getVisit());
        System.out.println(apprenticeAcademicYear.getOralExam());
        System.out.println(apprenticeAcademicYear.getMissions());
        System.out.println(apprenticeAcademicYear.getNotes());
        this.academicYearService.modifyAcademicYear(id,apprenticeAcademicYear);
        return "redirect:/academicYear/get-apprentice-academic-year/" + id;
    }

    @PostMapping("/create-apprentice-academic-year")
    public String createApprenticeAcademicYear(@ModelAttribute AcademicYear apprenticeAcademicYear)
    {
        System.out.println(apprenticeAcademicYear);
        System.out.println(apprenticeAcademicYear.getApprentice());
        System.out.println(apprenticeAcademicYear.getNotes());
        System.out.println(apprenticeAcademicYear.getMentor());
        System.out.println(apprenticeAcademicYear.getCompany());
        System.out.println(apprenticeAcademicYear.getVisit());
        System.out.println(apprenticeAcademicYear.getOralExam());
        System.out.println(apprenticeAcademicYear.getMissions());
        System.out.println(apprenticeAcademicYear.getNotes());



        return "redirect:/academicYear/get-apprentice-academic-year/" + apprenticeAcademicYear.getId();
    }

    @PostMapping("/create-academic-year")
    public String createAcademicYear()
    {
        return "redirect:/apprentice/get-dashboard";
    }
}
