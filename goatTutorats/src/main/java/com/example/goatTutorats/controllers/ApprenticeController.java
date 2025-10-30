package com.example.goatTutorats.controllers;

import com.example.goatTutorats.dtos.ApprenticeResearchCriteriaDTO;
import com.example.goatTutorats.dtos.YearDTO;
import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.entities.Year;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.services.ApprenticeService;
import com.example.goatTutorats.services.TutorService;
import com.example.goatTutorats.services.YearService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("apprentice")
public class ApprenticeController {

    private final TutorService tutorService;
    private final ApprenticeService apprenticeService;
    private final YearService yearService;

    public ApprenticeController(ApprenticeService apprenticeService,  TutorService tutorService,  YearService yearService) {
        this.apprenticeService = apprenticeService;
        this.tutorService = tutorService;
        this.yearService = yearService;
    }

    @GetMapping("/get-dashboard")
    public String dashboardForLastYear(Principal principal, Model model) {
        // retrieve connected tutor information
        String userName = principal.getName();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);

        // retrieve all years
        List<Year> years = this.yearService.getAllYearsByDescendingOrder();
        model.addAttribute("years", years);
        model.addAttribute("selectedYearForm", new YearDTO(years.isEmpty() ? null : years.getLast().getId()));
        model.addAttribute("selectedYear", years.isEmpty() ? null : years.getLast());

        // retrieve all apprentices for this tutor for the last year
        Tutor tutor = this.tutorService.getTutorByUsername(userName);

        List<ApprenticeRecordDTO> apprentices = years.isEmpty() ? List.of() : apprenticeService.getApprenticesByTutorForThisYear(
                tutor.getId(),
                years.getLast().getId()
        );
        model.addAttribute("apprentices", apprentices);

        return "dashboard";
    }

    @GetMapping("/get-dashboard-for-particular-year")
    public String dashboardForParticularYear(@ModelAttribute YearDTO selectedYear, Principal principal, Model model, RedirectAttributes redirectAttributes) {
        // retrieve connected tutor information
        String userName = principal.getName();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);

        // retrieve all years
        List<Year> years = this.yearService.getAllYearsByDescendingOrder();
        model.addAttribute("years", years);
        model.addAttribute("selectedYearForm", selectedYear);
        model.addAttribute("selectedYear", this.yearService.getYearById(selectedYear.getSelectedYearId()));

        // retrieve all apprentices for this tutor for the selected year
        Tutor tutor = this.tutorService.getTutorByUsername(userName);

        List<ApprenticeRecordDTO> apprentices;
        try{
            apprentices = apprenticeService.getApprenticesByTutorForThisYear(
                    tutor.getId(),
                    selectedYear.getSelectedYearId()
            );
        }
        catch (CustomEntityNotFoundException exception){
            redirectAttributes.addFlashAttribute("errorMessage", "Année introuvable");
            return "redirect:/apprentice/get-dashboard";
        }

        model.addAttribute("apprentices", apprentices);

        return "dashboard";
    }

    @GetMapping("/get-apprentice-research")
    public String apprenticeResearch(Principal principal, Model model) {
        // retrieve connected tutor information
        String userName = principal.getName();

        // create empty object to store apprentice research criteria
        ApprenticeResearchCriteriaDTO researchCriteriaDTO = new ApprenticeResearchCriteriaDTO();

        // total number of apprentice for all academic years for comparison
        int total_number = apprenticeService.getTotalNumber();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);
        model.addAttribute("researchCriteriaDTO", researchCriteriaDTO);
        model.addAttribute("totalNumber",total_number);

        return "apprentice-research";
    }

    @GetMapping("/get-apprentice-searched-by-criteria")
    public String getApprenticeResearch(Principal principal, Model model, @Valid @ModelAttribute ApprenticeResearchCriteriaDTO researchCriteriaDTO, BindingResult bindingResult) {
        String userName = principal.getName();
        model.addAttribute("username", userName);

        // total number of apprentice for all academic years for comparison
        int total_number = apprenticeService.getTotalNumber();

        // research apprentices and store result in model
        List<ApprenticeRecordDTO> apprenticeSearched = this.apprenticeService.researchApprentices(researchCriteriaDTO);
        model.addAttribute("apprenticeSearched", apprenticeSearched);
        model.addAttribute("researchCriteriaDTO", researchCriteriaDTO);
        model.addAttribute("totalNumber",total_number);

        return "apprentice-research";
    }
}



