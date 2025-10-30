package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.entities.Year;
import com.example.goatTutorats.enums.StudyLevel;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.AcademicYearRepository;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service for managing AcademicYear entities.
 */
@Service
public class AcademicYearService {

    private final AcademicYearRepository academicYearRepository;
    private final ApprenticeRepository apprenticeRepository;

    /**
     * Inject AcademicYear and Apprentice repositories.
     * @param academicYearRepository AcademicYear repository
     * @param apprenticeRepository Apprentice repository
     */
    public AcademicYearService(AcademicYearRepository academicYearRepository, ApprenticeRepository apprenticeRepository)
    {
        this.academicYearRepository = academicYearRepository;
        this.apprenticeRepository = apprenticeRepository;
    }

    /**
     * Find academic year by its id.
     * @param id the academic year id
     * @return the academic year
     */
    public AcademicYear findById(UUID id){
        return this.academicYearRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException(id.toString()));
    }

    /**
     * Find academic year by apprentice id and year id.
     * @param apprenticeId the apprentice id
     * @param yearId the year id
     * @return list of academic year ids
     */
    public List<UUID> findAcademicYearByApprenticeYear(UUID apprenticeId, UUID yearId){
        return this.academicYearRepository.findAcademicYearByApprenticeAndYear(apprenticeId, yearId);
    }

    /**
     * Modify an existing academic year.
     * @param idAcademicYear the academic year id
     * @param academicYearModified the modified academic year
     */
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

    /**
     * Add a new apprentice academic year and link it to its tutor.
     * @param academicYearToAdd the academic year to add
     * @param apprenticeTutor the tutor of the apprentice
     * @return the saved academic year
     */
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

    /**
     * Create academic year for all apprentices for the next year.
     * @param currentYear the current academic year
     * @param nextYear the next academic year
     * @param studyLevel the last study level
     */
    @Transactional
    public void createAcademicYear(Year currentYear, Year nextYear, StudyLevel studyLevel){
        // archive all apprentices that are already in last study level
        List<UUID> apprenticeIdsToArchive = this.apprenticeRepository.findApprenticesToArchive(
                studyLevel,
                currentYear.getId()
        );
        this.apprenticeRepository.archiveApprenticesById(apprenticeIdsToArchive);

        // retrieve all apprentice academic year that are not archived, for the current academic year
        List<AcademicYear> apprenticeAcademicYears = this.academicYearRepository.findApprenticeAcademicYearNotArchivedByYear(currentYear.getId());

        // iterate other each apprentice academic year
        for (AcademicYear apprenticeAcademicYear : apprenticeAcademicYears) {

            // check this apprentice doesn't have an academic year for the next year
            if (!this.findAcademicYearByApprenticeYear(apprenticeAcademicYear.getApprentice().getId(), nextYear.getId()).isEmpty()){
                continue;
            }

            // create new academic year for this apprentice and fill it with previous academic year info
        AcademicYear newApprenticeAcademicYear = createNewAcademicYearWithPreviousAcademicYear(nextYear, apprenticeAcademicYear);

            // save new academic year in db
            this.academicYearRepository.save(newApprenticeAcademicYear);
        }
    }

    /**
     * Get new academic year for apprentice.
     * @param nextYear the next year
     * @param apprenticeAcademicYear the apprentice academic year
     * @return the new academic year
     */
    private static AcademicYear createNewAcademicYearWithPreviousAcademicYear(Year nextYear, AcademicYear apprenticeAcademicYear) {
        AcademicYear newApprenticeAcademicYear = new AcademicYear();
        newApprenticeAcademicYear.setYear(nextYear);
        newApprenticeAcademicYear.setApprentice(apprenticeAcademicYear.getApprentice());
        newApprenticeAcademicYear.setCompany(apprenticeAcademicYear.getCompany());
        newApprenticeAcademicYear.setMentor(apprenticeAcademicYear.getMentor());

        // update study level
        StudyLevel newStudyLevel = apprenticeAcademicYear.getStudyLevel() == StudyLevel.ING1 ? StudyLevel.ING2 : StudyLevel.ING3;
        newApprenticeAcademicYear.setStudyLevel(newStudyLevel);
        return newApprenticeAcademicYear;
    }
}
