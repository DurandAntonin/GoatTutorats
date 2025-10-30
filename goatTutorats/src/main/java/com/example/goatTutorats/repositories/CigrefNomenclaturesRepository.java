package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.CigrefNomenclatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing {@link CigrefNomenclatures} entities.
 * <p>
 * Extends Spring Data JPA's {@link JpaRepository} to provide standard CRUD operations
 * and additional JPA-based queries for Cigref nomenclatures.
 * </p>
 *
 * The generic parameters are:
 * <ul>
 *     <li>{@code CigrefNomenclatures} - the entity type</li>
 *     <li>{@code UUID} - the type of the entity's primary key</li>
 * </ul>
 *
 * By annotating with {@link Repository}, this interface is recognized as a Spring
 * Data repository and automatically implemented at runtime.
 */
@Repository
public interface CigrefNomenclaturesRepository extends JpaRepository<CigrefNomenclatures, UUID> {
    // Additional custom queries can be defined here if needed
}
