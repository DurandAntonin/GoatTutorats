package com.example.goatTutorats.repositories;
import com.example.goatTutorats.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends  JpaRepository<Tutor,Integer> {

    Optional<Tutor> getTutorsByUsername(String username);
}
