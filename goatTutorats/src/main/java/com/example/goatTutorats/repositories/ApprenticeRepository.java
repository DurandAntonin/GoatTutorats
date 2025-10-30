package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.enums.StudyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for accessing Apprentice entities.
 */
public interface ApprenticeRepository extends JpaRepository<Apprentice, UUID> {

    /**
     * Finds the IDs of apprentices to be archived based on the specified study level and year ID.
     * @param studyLevel the study level of the apprentices
     * @param yearId the unique identifier of the year
     * @return a list of apprentice unique identifiers to be archived
     */
    @Query("SELECT DISTINCT a.id " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.year y " +
            "WHERE ay.studyLevel = :studyLevel " +
            "AND a.archived = FALSE " +
            "AND y.id = :yearId")
    List<UUID> findApprenticesToArchive(@Param("studyLevel") StudyLevel studyLevel,
                                 @Param("yearId") UUID yearId);

    /**
     * Marks the apprentices with the specified IDs as archived.
     *
     * @param ids a list of apprentice unique identifiers to be archived
     */
    @Modifying
    @Query("UPDATE Apprentice a " +
            "SET a.archived = TRUE " +
            "WHERE a.id IN :ids")
    void archiveApprenticesById(@Param("ids")  List<UUID> ids);

    /**
     * Returns the total number of apprentices in the database.
     *
     * @return the count of all apprentices
     */
    @Query(value = "SELECT COUNT(*) FROM apprentice", nativeQuery = true)
    int getTotalNumber();

}
