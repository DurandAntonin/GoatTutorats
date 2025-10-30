package com.example.goatTutorats.dtos;

import com.example.goatTutorats.enums.StudyLevel;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Apprentice Record.
 * @param apprenticeAcademicYearId the apprentice academic year ID
 * @param year the year
 * @param lastName the last name of the apprentice
 * @param firstName the first name of the apprentice
 * @param email the email of the apprentice
 * @param program the program of the apprentice
 * @param major the major of the apprentice
 * @param companyName the company name where the apprentice is placed
 * @param mentorLastName the last name of the mentor
 * @param mentorFirstName the first name of the mentor
 * @param studyLevel the study level of the apprentice
 * @param missions the list of mission records associated with the apprentice
 */
public record ApprenticeRecordDTO(UUID apprenticeAcademicYearId, LocalDate year, String lastName, String firstName, String email, String program, String major, String companyName, String mentorLastName, String mentorFirstName, StudyLevel studyLevel, List<MissionRecordDTO> missions) {}
