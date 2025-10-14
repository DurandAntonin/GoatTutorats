package com.example.goatTutorats.services;

import com.example.goatTutorats.entities.Tutor;
import com.example.goatTutorats.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> getTutors() {
        return tutorRepository.findAll();
    }

}
