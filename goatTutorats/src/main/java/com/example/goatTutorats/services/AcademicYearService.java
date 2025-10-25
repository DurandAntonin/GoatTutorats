package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.enums.StudyLevel;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.AcademicYearRepository;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AcademicYearService {

    private final AcademicYearRepository academicYearRepository;
    private final ApprenticeRepository apprenticeRepository;

    public AcademicYearService(AcademicYearRepository academicYearRepository, ApprenticeRepository apprenticeRepository)
    {
        this.academicYearRepository = academicYearRepository;
        this.apprenticeRepository = apprenticeRepository;
    }

    public AcademicYear findById(UUID id){
        return this.academicYearRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException(id.toString()));
    }

    @Transactional
    public void modifyAcademicYear(UUID idAcademicYear, AcademicYear academicYearModified)
    {
        AcademicYear academicYearToModify = this.academicYearRepository.findById(idAcademicYear).orElseThrow(() -> new CustomEntityNotFoundException(idAcademicYear.toString()));

        // update apprentice academic year nested objects without modifying sub object id
        BeanUtils.copyProperties(academicYearModified.getApprentice(),academicYearToModify.getApprentice(), "id", "tutor", "archived");
        BeanUtils.copyProperties(academicYearModified.getCompany(),academicYearToModify.getCompany(), "id");
        BeanUtils.copyProperties(academicYearModified.getMentor(),academicYearToModify.getMentor(), "id");
        BeanUtils.copyProperties(academicYearModified.getVisit(),academicYearToModify.getVisit(), "id");
        BeanUtils.copyProperties(academicYearModified.getReportEvaluation(),academicYearToModify.getReportEvaluation(), "id");
        BeanUtils.copyProperties(academicYearModified.getOralExam(),academicYearToModify.getOralExam(), "id");

        academicYearToModify.getMissions().clear();
        academicYearToModify.getMissions().addAll(academicYearModified.getMissions());

        academicYearToModify.getNotes().clear();
        academicYearToModify.getNotes().addAll(academicYearModified.getNotes());

        // update apprentice academic year without modifying its sub objects
        BeanUtils.copyProperties(academicYearModified,academicYearToModify,
                "id", "studyLevel", "year",
                "apprentice", "company", "mentor",
                "missions", "visit", "reportEvaluation",
                "oralExam", "notes"
        );

        this.academicYearRepository.save(academicYearToModify);
    }

    @Transactional
    public AcademicYear addApprenticeAcademicYear(AcademicYear academicYearToAdd, Tutor apprenticeTutor){
        // set academic year id to null
        academicYearToAdd.setId(null);
        academicYearToAdd.getApprentice().setArchived(false);
        academicYearToAdd.setStudyLevel(StudyLevel.ING1);

        // link apprentice to tutor
        academicYearToAdd.getApprentice().setTutor(apprenticeTutor);

        // save academic year to db and return it
        return this.academicYearRepository.save(academicYearToAdd);
    }

    @Transactional
    public void createAcademicYear(LocalDate currentAcademicYearDate, StudyLevel studyLevel){
        // archive all apprentices that are already in last study level
        List<UUID> apprenticeIdsToArchive = this.apprenticeRepository.findApprenticesToArchive(
                studyLevel,
                currentAcademicYearDate.getYear()
        );
        this.apprenticeRepository.archiveApprenticesById(apprenticeIdsToArchive);

        // retrieve all apprentice academic year that are not archived, for the current academic year
        List<AcademicYear> apprenticeAcademicYears = this.academicYearRepository.findApprenticeAcademicYearNotArchivedByYear(currentAcademicYearDate.getYear());

        // iterate other each apprentice academic year
        for (AcademicYear apprenticeAcademicYear : apprenticeAcademicYears) {

            // create new academic year for this apprentice and fill it with previous academic year info
            AcademicYear newApprenticeAcademicYear = new AcademicYear();
            newApprenticeAcademicYear.setYear(currentAcademicYearDate.plusYears(1));
            newApprenticeAcademicYear.setApprentice(apprenticeAcademicYear.getApprentice());
            newApprenticeAcademicYear.setCompany(apprenticeAcademicYear.getCompany());
            newApprenticeAcademicYear.setMentor(apprenticeAcademicYear.getMentor());

            // update study level
            StudyLevel newStudyLevel = apprenticeAcademicYear.getStudyLevel() == StudyLevel.ING1 ? StudyLevel.ING2 : StudyLevel.ING3;
            newApprenticeAcademicYear.setStudyLevel(newStudyLevel);

            // save new academic year in db
            this.academicYearRepository.save(newApprenticeAcademicYear);
        }
    }
}
