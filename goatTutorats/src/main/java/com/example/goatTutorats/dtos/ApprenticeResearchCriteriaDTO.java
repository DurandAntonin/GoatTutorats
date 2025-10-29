package com.example.goatTutorats.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApprenticeResearchCriteriaDTO{
    String apprenticeName;
    String companyName;
    String missionKeywords;

    @NotNull
    int academicYear;
}
