package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.enums.StudyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, UUID> {
    @Query("SELECT ay " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "WHERE a.archived = FALSE " +
            "AND FUNCTION('YEAR', ay.year) = :year")
    List<AcademicYear> findApprenticeAcademicYearNotArchivedByYear(@Param("year") int year);
}
