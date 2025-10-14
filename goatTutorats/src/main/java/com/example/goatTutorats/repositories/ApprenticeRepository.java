package com.example.goatTutorats.repositories;
import com.example.goatTutorats.entities.Apprentice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprenticeRepository extends JpaRepository<Apprentice,Integer> {

    @Query("SELECT DISTINCT a FROM Apprentice a " +
            "JOIN a.academicYears ay " +
            "WHERE a.tutor.id = :tuteurId " +
            "AND FUNCTION('YEAR', ay.year) = :annee")
    List<Apprentice> findByTutorAndYear(@Param("tuteurId") Long tuteurId,
                                        @Param("annee") int annee);


}
