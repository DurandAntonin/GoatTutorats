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

@Entity
@Table(name = "cigref_nomenclatures")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CigrefNomenclatures {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String internalReference;

    private String jobName;

    @Override
    public String toString() {
        return String.format("CigrefNomenclatures{"
                        + "id=%s, "
                        + "internalReference=%s, "
                        + "jobName=%s}",
                id, internalReference, jobName);
    }
}
