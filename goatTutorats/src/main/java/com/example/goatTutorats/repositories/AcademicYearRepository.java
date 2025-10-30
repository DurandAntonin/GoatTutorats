package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.AcademicYear;
import com.example.goatTutorats.enums.StudyLevel;
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
