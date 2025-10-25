package com.example.goatTutorats.repositories;

import com.example.goatTutorats.dtos.ApprenticeRecordDTO;
import com.example.goatTutorats.entities.Apprentice;
import com.example.goatTutorats.enums.StudyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
            "me.lastName, " +
            "me.firstName, " +
            "ay.studyLevel) " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "LEFT JOIN ay.company c " +
            "LEFT JOIN ay.missions m " +
            "LEFT JOIN ay.mentor me " +
            "WHERE a.tutor.id = :tutorId " +
            "AND a.archived IS FALSE " +
            "AND FUNCTION('YEAR', ay.year) = :year")
    List<ApprenticeRecordDTO> findByTutorAndYear(@Param("tutorId") UUID tutorId,
                                                 @Param("year") int year);

    @Query("SELECT DISTINCT a.id " +
            "FROM AcademicYear ay " +
            "LEFT JOIN ay.apprentice a " +
            "WHERE ay.studyLevel = :studyLevel " +
            "AND a.archived = FALSE " +
            "AND FUNCTION('YEAR', ay.year) = :year")
    List<UUID> findApprenticesToArchive(@Param("studyLevel") StudyLevel studyLevel,
                                 @Param("year") int year);

    @Modifying
    @Query("UPDATE Apprentice a " +
            "SET a.archived = TRUE " +
            "WHERE a.id IN :ids")
    void archiveApprenticesById(@Param("ids")  List<UUID> ids);
}
