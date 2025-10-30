package com.example.goatTutorats.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Cigref nomenclature entry.
 * <p>
 * This entity maps to the "cigref_nomenclatures" table in the database and stores
 * the internal reference and the job name for each Cigref role.
 * </p>
 * <p>
 * Lombok annotations are used to generate getters, setters, equals/hashCode,
 * no-argument constructor, and all-arguments constructor automatically.
 * </p>
 */
@Entity
@Table(name = "cigref_nomenclatures")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CigrefNomenclatures {

    /**
     * Primary key of the entity, generated as a UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Internal reference code for the Cigref job.
     */
    private String internalReference;

    /**
     * Name of the job or role according to the Cigref nomenclature.
     */
    private String jobName;

    /**
     * Returns a string representation of the entity.
     *
     * @return a formatted string containing id, internalReference, and jobName
     */
    @Override
    public String toString() {
        return String.format("CigrefNomenclatures{"
                        + "id=%s, "
                        + "internalReference=%s, "
                        + "jobName=%s}",
                id, internalReference, jobName);
    }
}
