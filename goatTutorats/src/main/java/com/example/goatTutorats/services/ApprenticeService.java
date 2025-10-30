package com.example.goatTutorats.services;

import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import com.example.goatTutorats.dtos.ApprenticeResearchCriteriaDTO;
import com.example.goatTutorats.dtos.MissionRecordDTO;
import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.enums.StudyLevel;
import com.example.goatTutorats.repositories.AcademicYearRepository;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for managing Apprentice entities.
 */
@Service
public class ApprenticeService {

    /**
     * Apprentice repository.
     */
    private final ApprenticeRepository apprenticeRepository;
    /**
     * Year service.
     */
    private final YearService yearService;
    /**
     * Academic year repository.
     */
    private final AcademicYearRepository academicYearRepository;

    /**
     * Inject Apprentice repository and Year service.
     * @param apprenticeRepository Apprentice repository
     * @param yearService Year service
     * @param academicYearRepository Academic year repository
     */
    public ApprenticeService(ApprenticeRepository apprenticeRepository,  YearService yearService,  AcademicYearRepository academicYearRepository)
    {
        this.apprenticeRepository = apprenticeRepository;
        this.yearService = yearService;
        this.academicYearRepository = academicYearRepository;
    }

    /**
     * Get all apprentices for a given tutor and year.
     * @param tutorId the tutor id
     * @param yearId the year id
     * @return list of apprentices
     */
    public List<ApprenticeRecordDTO> getApprenticesByTutorForThisYear(UUID tutorId, UUID yearId) {
        // check year exists
        this.yearService.getYearById(yearId);

        // retrieve all apprentice academic info for this year and this tutor
        List<AcademicYear> apprenticeAcademicYearList = this.academicYearRepository.getApprenticeAcademicYearInfoByYearAndTutor(tutorId, StudyLevel.ING3, yearId);

        // map each apprentice academic year info to a dto object
        return this.mapApprenticeAcademicYearToApprenticeDTO(apprenticeAcademicYearList);
    }

    /**
     * Research apprentices according to criteria.
     * @param researchCriteriaDTO the research criteria
     * @return list of apprentices
     */
    public List<ApprenticeRecordDTO> researchApprentices(ApprenticeResearchCriteriaDTO researchCriteriaDTO) {
        return this.mapApprenticeAcademicYearToApprenticeDTO(this.academicYearRepository.researchApprentices(
                researchCriteriaDTO.getApprenticeName(),
                researchCriteriaDTO.getCompanyName(),
                researchCriteriaDTO.getMissionKeywords(),
                researchCriteriaDTO.getAcademicYear()
        ));
    }

    /**
     * Map apprentice academic year info to apprentice dto.
     * @param apprenticeAcademicYearList the apprentice academic year list
     * @return list of apprentice record dto
     */
    public List<ApprenticeRecordDTO> mapApprenticeAcademicYearToApprenticeDTO(List<AcademicYear> apprenticeAcademicYearList){
        // map each apprentice academic year info to a dto object
        return apprenticeAcademicYearList.stream().map((apprenticeAcademicYear) -> {

            // retrieve all mission information
            List<MissionRecordDTO> missionRecordDTOList = apprenticeAcademicYear.getMissions().stream().map((mission) -> new MissionRecordDTO(mission.getKeywords())).toList();

            // store other info into dto
            return new ApprenticeRecordDTO(
                    apprenticeAcademicYear.getId(), apprenticeAcademicYear.getYear().getYear(), apprenticeAcademicYear.getApprentice().getLastName(),
                    apprenticeAcademicYear.getApprentice().getFirstName(), apprenticeAcademicYear.getApprentice().getEmail(),
                    apprenticeAcademicYear.getApprentice().getProgram(), apprenticeAcademicYear.getApprentice().getMajor(),
                    apprenticeAcademicYear.getCompany().getName(),  apprenticeAcademicYear.getMentor().getLastName(),
                    apprenticeAcademicYear.getMentor().getFirstName(), apprenticeAcademicYear.getStudyLevel(), missionRecordDTOList
            );
        }).toList();
    }

    /**
     * Get total number of apprentices.
     * @return the total number of apprentices
     */
    public int getTotalNumber()
    {
        return this.apprenticeRepository.getTotalNumber();
    }
}
