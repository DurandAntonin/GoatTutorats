package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing Year entities.
 */
public interface YearRepository extends JpaRepository<Year, UUID> {

    /**
     * Retrieves the most recent Year entity based on the year value in descending order.
     * @return an Optional containing the latest Year entity if it exists, otherwise an empty Optional.
     */
    @Query("SELECT y " +
            "FROM Year y " +
            "ORDER BY y.year DESC " +
            "LIMIT 1")
    Optional<Year> getLastYear();

    /**
     * Retrieves all Year entities ordered by the year value in ascending order.
     * @return a list of Year entities sorted from earliest to latest.
     */
    @Query("SELECT y " +
            "FROM Year y " +
            "ORDER BY y.year ASC ")
    List<Year> getAllYearsByAscendingOrder();
}
