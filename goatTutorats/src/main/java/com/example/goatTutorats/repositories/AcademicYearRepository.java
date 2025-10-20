package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, UUID> {

    @Query("SELECT ay FROM AcademicYear ay " +
            "WHERE ay.apprentice.id = :apprenticeId " +
            "AND FUNCTION('YEAR', ay.year) = :currentYear")
    Optional<AcademicYear> findByApprenticeAndYear(@Param("apprenticeId") UUID apprenticeId,
                                                   @Param("currentYear") int currentYear);

}
