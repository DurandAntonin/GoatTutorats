package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.Year;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.YearRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class YearService {

    private final YearRepository yearRepository;

    /**
     * Inject Year repository.
     * @param yearRepository Year repository
     */
    public YearService(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    public List<Year> getAllYearsByDescendingOrder() {
        return yearRepository.getAllYearsByAscendingOrder();
    }

    public Year getYearById(UUID yearId) {
        return this.yearRepository.findById(yearId).orElseThrow(() -> new CustomEntityNotFoundException(yearId.toString()));
    }

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
