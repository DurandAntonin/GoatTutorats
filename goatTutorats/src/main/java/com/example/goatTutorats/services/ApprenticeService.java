package com.example.goatTutorats.services;

import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import com.example.goatTutorats.dtos.ApprenticeResearchCriteriaDTO;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import org.springframework.stereotype.Service;

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

    public List<ApprenticeRecordDTO> researchApprentices(ApprenticeResearchCriteriaDTO researchCriteriaDTO) {
        return apprenticeRepository.researchApprentices(
                researchCriteriaDTO.getApprenticeName(),
                researchCriteriaDTO.getCompanyName(),
                researchCriteriaDTO.getCompanyKeywords(),
                researchCriteriaDTO.getAcademicYear()
        );
    }
}
