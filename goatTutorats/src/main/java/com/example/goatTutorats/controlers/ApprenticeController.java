package com.example.goatTutorats.controlers;

import com.example.goatTutorats.dtos.ApprenticeUpdateDTO;
import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.services.AcademicYearService;
import com.example.goatTutorats.services.ApprenticeService;
import com.example.goatTutorats.services.TutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Year;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Controller
@RequestMapping("apprentice")
public class ApprenticeController {

    private final TutorService tutorService;

    private final ApprenticeService apprenticeService;

    private final AcademicYearService academicYearService;

    public ApprenticeController(ApprenticeService apprenticeService,  TutorService tutorService, AcademicYearService academicYearService) {
        this.apprenticeService = apprenticeService;
        this.tutorService = tutorService;
        this.academicYearService = academicYearService;
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
        int currentYear = Year.now().getValue();

        List<ApprenticeRecordDTO> apprentices = apprenticeService.getApprenticesByTutorForThisYear(tutor.getId(), currentYear);
        model.addAttribute("apprentices", apprentices);
        model.addAttribute("currentYear", currentYear);

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

        // retrieve academic year to get all info on the apprentice
        Optional<AcademicYear> accademicYearForThisApprentice = this.academicYearService.findById(id);

        if (accademicYearForThisApprentice.isEmpty()) {
            return "errors/error-404";
        }

        Apprentice apprentice = accademicYearForThisApprentice.get().getApprentice();

        model.addAttribute("accademicYearForThisApprentice",accademicYearForThisApprentice);

        // store form name, action and method
        model.addAttribute("formName", apprentice.getFirstName()+" "+ apprentice.getLastName());
        model.addAttribute("formAction", "/apprentice/updateApprentice/" + id);
        model.addAttribute("formMethod", "PATCH");

        model.addAttribute("apprentice", apprentice);
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



