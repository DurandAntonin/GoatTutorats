package com.example.goatTutorats.dtos;

import com.example.goatTutorats.entities.AcademicYear;
import lombok.Data;

import java.util.List;

@Data
public class ApprenticeUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String programme;
    private String majeure;
    private AcademicYear newAcademicYear;
}

