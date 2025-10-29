package com.example.goatTutorats.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class YearDTO {
    private UUID selectedYearId;
}
