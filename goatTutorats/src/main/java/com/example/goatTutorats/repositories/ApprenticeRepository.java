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
            "a.lastName, " +
            "a.firstName, " +
            "a.email, " +
            "a.programme, " +
            "a.majeure, " +
            "c.name, " +
            "m.targetJob) " +
            "FROM Apprentice a " +
            "JOIN a.academicYears ay " +
            "JOIN ay.company c " +
            "JOIN ay.missions m " +
            "WHERE a.tutor.id = :tuteurId " +
            "AND FUNCTION('YEAR', ay.year) = :annee")
    List<ApprenticeRecordDTO> findByTutorAndYear(@Param("tuteurId") UUID tuteurId,
                                                 @Param("annee") int annee);
}
