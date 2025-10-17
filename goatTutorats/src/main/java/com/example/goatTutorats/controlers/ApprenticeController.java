package com.example.goatTutorats.controlers;

import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import com.example.goatTutorats.services.ApprenticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("apprentice")
public class ApprenticeController {

    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @GetMapping("getApprenticeByTutorAndCurrentYear/{idTutor}")
    public List<ApprenticeRecordDTO> getApprenticeByTutorAndCurrentYear(@PathVariable("idTutor") UUID idT)
    {
        return apprenticeService.getApprenticesByTutorForThisYear(idT);
    }
}



