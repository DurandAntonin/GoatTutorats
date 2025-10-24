package com.example.goatTutorats.services;

import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import com.example.goatTutorats.dtos.ApprenticeUpdateDTO;
import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.*;

@Service
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;

    public ApprenticeService(ApprenticeRepository apprenticeRepository)
    {
        this.apprenticeRepository = apprenticeRepository;
    }

    public List<ApprenticeRecordDTO> getApprenticesByTutorForThisYear(UUID tutorId, int currentYear) {
        return apprenticeRepository.findByTutorAndYear(tutorId, currentYear);
    }
}
