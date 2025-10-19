package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public Tutor getTutorByUsername(String username) {
        return this.tutorRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("No tutor found with username: " + username));
    }

}
