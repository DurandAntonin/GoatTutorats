package com.example.goatTutorats.controlers;

import com.example.goatTutorats.services.ApprenticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ApprenticeController {

    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }
}
