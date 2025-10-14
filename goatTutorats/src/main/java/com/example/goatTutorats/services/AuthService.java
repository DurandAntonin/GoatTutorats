package com.example.goatTutorats.services;

import com.example.goatTutorats.repositories.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private TutorRepository tutorRepository;

    public AuthService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }
}
