package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.TutorRepository;
import org.springframework.stereotype.Service;

/**
 * This service is used to manage tutors.
 */
@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    /**
     * Inject Tutor repository.
     * @param tutorRepository Tutor repository
     */
    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    /**
     * Get tutor by username.
     * @param username username of the tutor
     * @return an instance of Tutor
     */
    public Tutor getTutorByUsername(String username) {
        return this.tutorRepository.findByUsername(username).orElseThrow(() -> new CustomEntityNotFoundException(username));
    }

}
