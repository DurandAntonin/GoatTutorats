package com.example.goatTutorats.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ApprenticeResearchCriteriaDTO{
    String apprenticeName;
    String companyName;
    String missionKeywords;
    int academicYear;

    public ApprenticeResearchCriteriaDTO(){
        this.apprenticeName = "";
        this.companyName = "";
        this.missionKeywords = "";
        this.academicYear = LocalDate.now().getYear();
    }
}
