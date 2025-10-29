package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface YearRepository extends JpaRepository<Year, UUID> {

    @Query("SELECT y " +
            "FROM Year y " +
            "ORDER BY y.year DESC " +
            "LIMIT 1")
    Optional<Year> getLastYear();

    @Query("SELECT y " +
            "FROM Year y " +
            "ORDER BY y.year ASC ")
    List<Year> getAllYearsByAscendingOrder();
}
