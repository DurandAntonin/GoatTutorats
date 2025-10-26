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
    String companyKeywords;
    int academicYear;

    public ApprenticeResearchCriteriaDTO(){
        this.apprenticeName = "";
        this.companyName = "";
        this.companyKeywords = "";
        this.academicYear = LocalDate.now().getYear();
    }
}
