package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.enums.StudyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ApprenticeRepository extends JpaRepository<Apprentice, UUID> {
    @Query("SELECT DISTINCT a.id " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.year y " +
            "WHERE ay.studyLevel = :studyLevel " +
            "AND a.archived = FALSE " +
            "AND y.id = :yearId")
    List<UUID> findApprenticesToArchive(@Param("studyLevel") StudyLevel studyLevel,
                                 @Param("yearId") UUID yearId);

    @Modifying
    @Query("UPDATE Apprentice a " +
            "SET a.archived = TRUE " +
            "WHERE a.id IN :ids")
    void archiveApprenticesById(@Param("ids")  List<UUID> ids);

    @Query(value = "SELECT COUNT(*) FROM apprentice", nativeQuery = true)
    int getTotalNumber();

}
