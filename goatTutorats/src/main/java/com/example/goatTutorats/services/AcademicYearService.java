package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.repositories.AcademicYearRepository;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AcademicYearService {

    private final AcademicYearRepository academicYearRepository;

    public AcademicYearService(AcademicYearRepository academicYearRepository)
    {
        this.academicYearRepository = academicYearRepository;
    }

    public Optional<AcademicYear> findById(UUID id){
        return this.academicYearRepository.findById(id);
    }

    public Optional<AcademicYear> findByApprenticeAndYear(UUID id, int currentYear)
    {
        return this.academicYearRepository.findByApprenticeAndYear(id,currentYear);
    }


}
