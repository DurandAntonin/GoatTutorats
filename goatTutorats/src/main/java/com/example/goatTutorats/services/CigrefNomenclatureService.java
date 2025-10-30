package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.CigrefNomenclatures;
import com.example.goatTutorats.repositories.CigrefNomenclaturesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing Cigref nomenclatures.
 * <p>
 * Provides methods to retrieve Cigref job names and other related operations.
 * </p>
 */
@Service
public class CigrefNomenclatureService {

    /** Repository for accessing CigrefNomenclatures entities in the database. */
    private final CigrefNomenclaturesRepository repository;

    /**
     * Constructs a new {@code CigrefNomenclatureService} with the given repository.
     *
     * @param repository the repository used to access CigrefNomenclatures data
     */
    public CigrefNomenclatureService(CigrefNomenclaturesRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all job names from the Cigref nomenclature table.
     *
     * @return a list of job names as {@code List<String>}
     */
    public List<String> getAllJobNames() {
        return repository.findAll()
                .stream()
                .map(CigrefNomenclatures::getJobName)
                .collect(Collectors.toList());
    }
}
