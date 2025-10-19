package com.example.goatTutorats.repositories;

import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import com.example.goatTutorats.entities.Apprentice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ApprenticeRepository extends JpaRepository<Apprentice, UUID> {

    @Query("SELECT DISTINCT new com.example.goatTutorats.dtos.ApprenticeRecordDTO(" +
            "ay.id, " +
            "a.lastName, " +
            "a.firstName, " +
            "a.email, " +
            "a.program, " +
            "a.major, " +
            "c.name, " +
            "m.targetJob) " +
            "FROM Apprentice a " +
            "JOIN a.academicYears ay " +
            "JOIN ay.company c " +
            "JOIN ay.missions m " +
            "WHERE a.tutor.id = :tutorId " +
            "AND FUNCTION('YEAR', ay.year) = :year")
    List<ApprenticeRecordDTO> findByTutorAndYear(@Param("tutorId") UUID tutorId,
                                                 @Param("year") int year);
}
