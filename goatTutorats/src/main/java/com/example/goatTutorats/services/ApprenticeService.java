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

@Service
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;
    private final YearService yearService;
    private final AcademicYearRepository academicYearRepository;

    public ApprenticeService(ApprenticeRepository apprenticeRepository,  YearService yearService,  AcademicYearRepository academicYearRepository)
    {
        this.apprenticeRepository = apprenticeRepository;
        this.yearService = yearService;
        this.academicYearRepository = academicYearRepository;
    }

    public List<ApprenticeRecordDTO> getApprenticesByTutorForThisYear(UUID tutorId, UUID yearId) {
        // check year exists
        this.yearService.getYearById(yearId);

        // retrieve all apprentice academic info for this year and this tutor
        List<AcademicYear> apprenticeAcademicYearList = this.academicYearRepository.getApprenticeAcademicYearInfoByYearAndTutor(tutorId, StudyLevel.ING3, yearId);

        // map each apprentice academic year info to a dto object
        return this.mapApprenticeAcademicYearToApprenticeDTO(apprenticeAcademicYearList);
    }

    public List<ApprenticeRecordDTO> researchApprentices(ApprenticeResearchCriteriaDTO researchCriteriaDTO) {
        return this.mapApprenticeAcademicYearToApprenticeDTO(this.academicYearRepository.researchApprentices(
                researchCriteriaDTO.getApprenticeName(),
                researchCriteriaDTO.getCompanyName(),
                researchCriteriaDTO.getMissionKeywords(),
                researchCriteriaDTO.getAcademicYear()
        ));
    }

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

    public int getTotalNumber()
    {
        return this.apprenticeRepository.getTotalNumber();
    }
}
