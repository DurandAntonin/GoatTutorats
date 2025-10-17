package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;

    public ApprenticeService(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    public List<Apprentice> getApprenticesByTutorForThisYear(Long tutorId) {
        int currentYear = Year.now().getValue();
        return apprenticeRepository.findByTutorAndYear(tutorId, currentYear);
    }
}
