package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, UUID> {
    @Query("SELECT ay " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.year y " +
            "WHERE a.archived = FALSE " +
            "AND y.id = :yearId")
    List<AcademicYear> findApprenticeAcademicYearNotArchivedByYear(@Param("yearId") UUID yearId);

    @Query("SELECT DISTINCT ay.id " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.year y " +
            "WHERE a.id = :apprenticeId " +
            "AND y.id = :yearId")
    List<UUID> findAcademicYearByApprenticeAndYear(@Param("apprenticeId") UUID apprenticeId,
                                                                  @Param("yearId") UUID yearId);
}
