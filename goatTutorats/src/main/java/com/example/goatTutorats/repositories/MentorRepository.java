package com.example.goatTutorats.repositories;
import com.example.goatTutorats.entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentorRepository extends JpaRepository<Mentor, UUID> {

}
