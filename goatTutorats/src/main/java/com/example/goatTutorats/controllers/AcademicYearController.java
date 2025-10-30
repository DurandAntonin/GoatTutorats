package com.example.goatTutorats.controllers;

import com.example.goatTutorats.entities.*;
import com.example.goatTutorats.enums.StudyLevel;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.services.AcademicYearService;
import com.example.goatTutorats.services.CigrefNomenclatureService;
import com.example.goatTutorats.services.TutorService;
import com.example.goatTutorats.services.YearService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("academicYear")
public class AcademicYearController {

    private final AcademicYearService academicYearService;
    private final TutorService tutorService;
    private final YearService yearService;
    private final CigrefNomenclatureService cigrefNomenclatureService;

    public AcademicYearController(AcademicYearService academicYearService,  TutorService tutorService,  YearService yearService, CigrefNomenclatureService cigrefNomenclatureService) {
        this.academicYearService = academicYearService;
        this.tutorService = tutorService;
        this.yearService = yearService;
        this.cigrefNomenclatureService = cigrefNomenclatureService;
    }

    @GetMapping("/get-apprentice-academic-year/{id}")
    public String getApprenticeAcademicYearInformation(@PathVariable UUID id, Principal principal, Model model, RedirectAttributes redirectAttributes) {
        // store those information in model to access in html templates
        model.addAttribute("username", principal.getName());

        // retrieve academic year information for this apprentice
        AcademicYear apprenticeAcademicYear;
        try{
            apprenticeAcademicYear = this.academicYearService.findById(id);
        }
        catch (CustomEntityNotFoundException exception){
            redirectAttributes.addFlashAttribute("errorMessage", "Année académique introuvable pour l'apprenti(e) !");
            return "redirect:/apprentice/get-dashboard";
        }
        catch (Exception exception){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }

        // check apprentice is associated to current tutor
        if (!apprenticeAcademicYear.getApprentice().getTutor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Apprenti(e) non associé(e) au tuteur école actuel !");
        }

        model.addAttribute("apprenticeAcademicYear", apprenticeAcademicYear);

        // store form name, action and method
        model.addAttribute("formName", apprenticeAcademicYear.getApprentice().getFirstName() +" "+ apprenticeAcademicYear.getApprentice().getLastName());
        model.addAttribute("formAction", "/academicYear/update-apprentice-academic-year/" + id);
        model.addAttribute("formMethod", "PATCH");
        model.addAttribute("submitFormButtonName", "Mettre à jour");
        model.addAttribute("addApprenticeForm", false);

        return "apprentice";
    }

    @GetMapping("/get-apprentice-academic-year-creation")
    public String getApprenticeAcademicYearCreationPage(Principal principal, Model model) {
        // store those information in model to access in html templates
        model.addAttribute("username", principal.getName());

        // store form name, action and method
        model.addAttribute("formName", "Ajouter un apprenti");
        model.addAttribute("formAction", "/academicYear/create-apprentice-academic-year");
        model.addAttribute("formMethod", "POST");
        model.addAttribute("submitFormButtonName", "Ajouter");
        model.addAttribute("addApprenticeForm", true);

        // create empty apprentice academic year
        AcademicYear apprenticeAcademicYear = new AcademicYear();
        apprenticeAcademicYear.setMissions(List.of(new Mission()));
        apprenticeAcademicYear.setNotes(List.of(new Note()));

        // associate academic year to last year
        apprenticeAcademicYear.setYear(this.yearService.getLastYearOrCreateOne());

        model.addAttribute("apprenticeAcademicYear", apprenticeAcademicYear);

        // Add cigref jobs list
        List<String> cigrefJobs = cigrefNomenclatureService.getAllJobNames()
                .stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
        model.addAttribute("cigrefJobs", cigrefJobs);
        return "apprentice";
    }

    @PatchMapping("/update-apprentice-academic-year/{id}")
    public String updateApprenticeAcademicYear(@PathVariable UUID id, @ModelAttribute AcademicYear apprenticeAcademicYear, RedirectAttributes redirectAttributes)
    {
        try{
            this.academicYearService.modifyAcademicYear(id, apprenticeAcademicYear);
            redirectAttributes.addFlashAttribute("successMessage", "Apprenti(e) mis(e) à jour avec succès !");
        }
        catch (CustomEntityNotFoundException exception){
            redirectAttributes.addFlashAttribute("errorMessage", "Année académique introuvable pour l'apprenti(e) !");
        }
        catch (Exception exception){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }

        return "redirect:/academicYear/get-apprentice-academic-year/" + id;
    }

    @PostMapping("/create-apprentice-academic-year")
    public String createApprenticeAcademicYear(@ModelAttribute AcademicYear apprenticeAcademicYear, Principal principal,RedirectAttributes redirectAttributes)
    {
        // retrieve current tutor of future apprentice
        Tutor tutor;
        try{
            tutor = this.tutorService.getTutorByUsername(principal.getName());
        }
        catch (CustomEntityNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
        catch (Exception exception){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }

        // create new apprentice and return it
        AcademicYear newApprenticeAcademicYear = this.academicYearService.addApprenticeAcademicYear(apprenticeAcademicYear, tutor);
        redirectAttributes.addFlashAttribute("successMessage", "Apprenti(e) créé(e) avec succès !");

        return "redirect:/academicYear/get-apprentice-academic-year/" + newApprenticeAcademicYear.getId();
    }

    @PostMapping("/create-academic-year")
    public String createAcademicYear()
    {
        // first step is to retrieve current last year and to create a new year for new apprentice academic year
        Year currentYear = this.yearService.getLastYearOrCreateOne();
        Year nextYear = this.yearService.createYear();

        // second step is to create new academic year for apprentices
        try{
            this.academicYearService.createAcademicYear(currentYear, nextYear, StudyLevel.ING3);
        }
        catch (Exception exception){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
    }
        return "redirect:/apprentice/get-dashboard";
    }
}
