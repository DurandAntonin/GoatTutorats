package com.example.goatTutorats.services;

import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import com.example.goatTutorats.dtos.ApprenticeUpdateDTO;
import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.repositories.ApprenticeRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;

    public ApprenticeService(ApprenticeRepository apprenticeRepository)
    {
        this.apprenticeRepository = apprenticeRepository;
    }

    public Optional<Apprentice> findById(UUID id)
    {
        return this.apprenticeRepository.findById(id);
    }

    public List<ApprenticeRecordDTO> getApprenticesByTutorForThisYear(UUID tutorId, int currentYear) {
        return apprenticeRepository.findByTutorAndYear(tutorId, currentYear);
    }

    public Apprentice updateApprentice(UUID id, ApprenticeUpdateDTO dto) {
        Optional<Apprentice> optionalApprentice = apprenticeRepository.findById(id);

        if (optionalApprentice.isEmpty()) {
            throw new RuntimeException("Apprentice not found with id " + id);
        }

        Apprentice apprentice = optionalApprentice.get();

        if (dto.getFirstName() != null) apprentice.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) apprentice.setLastName(dto.getLastName());
        if (dto.getEmail() != null) apprentice.setEmail(dto.getEmail());
        if (dto.getPhone() != null) apprentice.setPhone(dto.getPhone());
        if (dto.getProgramme() != null) apprentice.setProgram(dto.getProgramme());
        if (dto.getMajeure() != null) apprentice.setMajor(dto.getMajeure());
        if (dto.getNewAcademicYear() != null) apprentice.getAcademicYears().add(dto.getNewAcademicYear());

        return apprenticeRepository.save(apprentice);
    }
}
