package com.example.goatTutorats.dtos;

import lombok.*;

import java.util.UUID;

/**
 * Data Transfer Object for Year selection.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class YearDTO {
    /**
     * The selected year ID.
     */
    private UUID selectedYearId;
}
