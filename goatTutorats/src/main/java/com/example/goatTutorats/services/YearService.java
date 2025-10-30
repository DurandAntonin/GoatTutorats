package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.Year;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.YearRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This service is used to manage years.
 */
@Service
public class YearService {

    /**
     * Year repository.
     */
    private final YearRepository yearRepository;

    /**
     * Inject Year repository.
     * @param yearRepository Year repository
     */
    public YearService(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    /**
     * Get all years by descending order.
     * @return list of years
     */
    public List<Year> getAllYearsByDescendingOrder() {
        return yearRepository.getAllYearsByAscendingOrder();
    }

    /**
     * Get year by its id.
     * @param yearId the year id
     * @return the year
     */
    public Year getYearById(UUID yearId) {
        return this.yearRepository.findById(yearId).orElseThrow(() -> new CustomEntityNotFoundException(yearId.toString()));
    }

    /**
     * Get last year or create one if none exists.
     * @return the last year
     */
    public Year getLastYearOrCreateOne(){
        // retrieve last year in db
        Optional<Year> lastExistingYear = this.yearRepository.getLastYear();

        // return year if exists
        if (lastExistingYear.isPresent()){
            return lastExistingYear.get();
        }

        // otherwise, create a new year and add it in db
        Year newYear = new Year();
        newYear.setYear(LocalDate.now());
        this.yearRepository.save(newYear);

        return newYear;
    }

    /**
     * Create the next year and save it in db.
     * @return the created year
     */
    public Year createYear(){
        // get last year in db
        Optional<Year> lastExistingYear = this.yearRepository.getLastYear();

        Year nextYear = new Year();
        if (lastExistingYear.isPresent()){
            nextYear.setYear(lastExistingYear.get().getYear());
            nextYear.setYear(nextYear.getYear().plusYears(1));
        }
        else{
            nextYear.setYear(LocalDate.now().plusYears(1));
        }

        this.yearRepository.save(nextYear);
        return nextYear;
    }
}
