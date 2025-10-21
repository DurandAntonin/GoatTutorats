package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.AcademicYearRepository;
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
}
