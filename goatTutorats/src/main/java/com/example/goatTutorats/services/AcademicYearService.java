package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.AcademicYearRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AcademicYearService {

    private final AcademicYearRepository academicYearRepository;

    public AcademicYearService(AcademicYearRepository academicYearRepository)
    {
        this.academicYearRepository = academicYearRepository;
    }

    public AcademicYear findById(UUID id){
        return this.academicYearRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException(id.toString()));
    }

    @Transactional
    public void modifyAcademicYear(UUID idAcademicYear, AcademicYear academicYearModified)
    {
        AcademicYear academicYearToModify = this.academicYearRepository.findById(idAcademicYear).orElseThrow(() -> new CustomEntityNotFoundException(idAcademicYear.toString()));

        // update apprentice academic year nested objects without modifying sub object id
        BeanUtils.copyProperties(academicYearModified.getApprentice(),academicYearToModify.getApprentice(), "id", "tutor");
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
        BeanUtils.copyProperties(academicYearModified,academicYearToModify, "id", "apprentice", "company", "mentor", "missions", "visit", "reportEvaluation", "oralExam", "notes");

        this.academicYearRepository.save(academicYearToModify);
    }

    @Transactional
    public AcademicYear addApprenticeAcademicYear(AcademicYear academicYearToAdd, Tutor apprenticeTutor){
        // set academic year id to null
        academicYearToAdd.setId(null);

        // link apprentice to tutor
        academicYearToAdd.getApprentice().setTutor(apprenticeTutor);

        // save academic year to db and return it
        return this.academicYearRepository.save(academicYearToAdd);
    }
}
