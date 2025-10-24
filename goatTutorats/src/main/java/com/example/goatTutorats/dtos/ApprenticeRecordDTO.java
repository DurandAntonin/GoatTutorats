package com.example.goatTutorats.dtos;

import com.example.goatTutorats.enums.StudyLevel;

import java.util.UUID;

public record ApprenticeRecordDTO(UUID apprenticeAcademicYearId, String lastName, String firstName, String email, String program, String major, String companyName, String mentorLastName, String mentorFirstName, StudyLevel studyLevel){}
