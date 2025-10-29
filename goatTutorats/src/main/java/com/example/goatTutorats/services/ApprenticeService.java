package com.example.goatTutorats.services;

import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import com.example.goatTutorats.dtos.ApprenticeRecordResearchDTO;
import com.example.goatTutorats.dtos.ApprenticeResearchCriteriaDTO;
import com.example.goatTutorats.enums.StudyLevel;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;
    private final YearService yearService;

    public ApprenticeService(ApprenticeRepository apprenticeRepository,  YearService yearService)
    {
        this.apprenticeRepository = apprenticeRepository;
        this.yearService = yearService;
    }

    public List<ApprenticeRecordDTO> getApprenticesByTutorForThisYear(UUID tutorId, UUID yearId) {
        // check year exists
        this.yearService.getYearById(yearId);

        // retrieve all apprentice info for this year
        return apprenticeRepository.findByTutorAndYear(tutorId, StudyLevel.ING3, yearId);
    }

    public List<ApprenticeRecordResearchDTO> researchApprentices(ApprenticeResearchCriteriaDTO researchCriteriaDTO) {
        return apprenticeRepository.researchApprentices(
                researchCriteriaDTO.getApprenticeName(),
                researchCriteriaDTO.getCompanyName(),
                researchCriteriaDTO.getMissionKeywords(),
                researchCriteriaDTO.getAcademicYear()
        );
    }

    public int getTotalNumber()
    {
        return this.apprenticeRepository.getTotalNumber();
    }
}
