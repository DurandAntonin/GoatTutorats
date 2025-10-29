package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.TutorRepository;
import org.springframework.stereotype.Service;


@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public Tutor getTutorByUsername(String username) {
        return this.tutorRepository.findByUsername(username).orElseThrow(() -> new CustomEntityNotFoundException(username));
    }

}
