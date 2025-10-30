package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.enums.StudyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for accessing AcademicYear entities.
 */
public interface AcademicYearRepository extends JpaRepository<AcademicYear, UUID> {

    /**
     * Retrieves all non-archived academic years for apprentices associated with a specific academic year.
     *
     * @param yearId the unique identifier of the academic year
     * @return a list of active (non-archived) apprentice academic year records
     */
    @Query("SELECT ay " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.year y " +
            "WHERE a.archived = FALSE " +
            "AND y.id = :yearId")
    List<AcademicYear> findApprenticeAcademicYearNotArchivedByYear(@Param("yearId") UUID yearId);

    /**
     * Retrieves the list of academic year IDs associated with a specific apprentice and academic year.
     *
     * @param apprenticeId the unique identifier of the apprentice
     * @param yearId the unique identifier of the academic year
     * @return a list of matching academic year IDs
     */
    @Query("SELECT DISTINCT ay.id " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.year y " +
            "WHERE a.id = :apprenticeId " +
            "AND y.id = :yearId")
    List<UUID> findAcademicYearByApprenticeAndYear(@Param("apprenticeId") UUID apprenticeId,
                                                                  @Param("yearId") UUID yearId);

    /**
     * Retrieves academic year records for apprentices supervised by a specific tutor and linked to a given academic year,
     * excluding archived apprentices matching the specified study level.
     *
     * @param tutorId the unique identifier of the tutor
     * @param studyLevel the study level used to filter out archived apprentices
     * @param yearId the unique identifier of the academic year
     * @return a list of academic year records matching the tutor and year criteria
     */
    @Query("SELECT ay " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.year y " +
            "LEFT JOIN ay.company c " +
            "LEFT JOIN ay.missions m " +
            "LEFT JOIN ay.mentor me " +
            "WHERE a.tutor.id = :tutorId " +
            "AND NOT (ay.studyLevel = :studyLevel " +
            "AND a.archived IS TRUE)" +
            "AND y.id = :yearId")
    List<AcademicYear> getApprenticeAcademicYearInfoByYearAndTutor(@Param("tutorId") UUID tutorId,
                                                                   @Param("studyLevel") StudyLevel studyLevel,
                                                                   @Param("yearId") UUID yearId);

    /**
     * Searches for academic year records of apprentices based on multiple optional criteria,
     * including apprentice name, company name, mission keywords, and academic year.
     *
     * @param apprenticeName partial or full apprentice last name to filter by
     * @param companyName partial or full company name to filter by
     * @param missionKeywords keywords to match within missions
     * @param academicYear the academic year to filter by; ignored if <= 0
     * @return a list of academic year records matching the specified search criteria, ordered by year
     */
    @Query("SELECT ay " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.year y " +
            "LEFT JOIN ay.company c " +
            "LEFT JOIN ay.missions m " +
            "LEFT JOIN ay.mentor me " +
            "WHERE a.lastName LIKE concat('%', :apprenticeName, '%') " +
            "AND c.name LIKE concat('%', :companyName, '%') " +
            "AND (:year <= 0 OR FUNCTION('YEAR', y.year) = :year) " +
            "AND (" +
            "( :missionKeywords = '' " +
            "  OR EXISTS (" +
            "SELECT ay1.id " +
            "FROM AcademicYear ay1 " +
            "LEFT JOIN ay1.missions m1 " +
            "WHERE ay1.id = ay.id " +
            "AND m1.keywords LIKE concat('%', :missionKeywords, '%')" +
            ")" +
            ")" +
            ") " +
            "ORDER BY y.year ")
    List<AcademicYear> researchApprentices(@Param("apprenticeName") String apprenticeName,
                                                     @Param("companyName") String companyName,
                                                     @Param("missionKeywords") String missionKeywords,
                                                     @Param("year") int academicYear);
}
