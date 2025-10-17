package com.example.goatTutorats.controlers;

import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.services.ApprenticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class ApprenticeController {

    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @GetMapping("/temp")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/apprentice")
    public String apprentice(Model model){
        model.addAttribute("apprentice", new Apprentice());
        return "apprentice";
    }
}
