package com.example.goatTutorats.controlers;

import com.example.goatTutorats.dtos.ApprenticeResearchCriteriaDTO;
import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.services.ApprenticeService;
import com.example.goatTutorats.services.TutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Year;
import java.util.*;

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
    public String dashboard(Principal principal, Model model) {
        // retrieve connected tutor information
        String userName = principal.getName();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);

        // retrieve all apprentices for this tutor
        Tutor tutor = this.tutorService.getTutorByUsername(userName);
        int currentYear = Year.now().getValue();

        List<ApprenticeRecordDTO> apprentices = apprenticeService.getApprenticesByTutorForThisYear(tutor.getId(), currentYear);
        model.addAttribute("apprentices", apprentices);
        model.addAttribute("currentYear", currentYear);

        return "dashboard";
    }

    @GetMapping("/get-apprentice-research")
    public String apprenticeResearch(Principal principal, Model model) {
        // retrieve connected tutor information
        String userName = principal.getName();

        // create empty object to store apprentice research criteria
        ApprenticeResearchCriteriaDTO researchCriteriaDTO = new ApprenticeResearchCriteriaDTO();

        // store those information in model to access in html templates
        model.addAttribute("username", userName);
        model.addAttribute("researchCriteriaDTO", researchCriteriaDTO);

        return "apprentice-research";
    }

    @GetMapping("/get-apprentice-searched-by-criteria")
    public String getApprenticeResearch(Principal principal, Model model, @ModelAttribute ApprenticeResearchCriteriaDTO researchCriteriaDTO) {
        String userName = principal.getName();
        model.addAttribute("username", userName);

        // research apprentices and store result in model
        List<ApprenticeRecordDTO> apprenticeSearched = this.apprenticeService.researchApprentices(researchCriteriaDTO);
        model.addAttribute("apprenticeSearched", apprenticeSearched);
        model.addAttribute("researchCriteriaDTO", researchCriteriaDTO);

        return "apprentice-research";
    }
}



