package com.example.goatTutorats.repositories;
import com.example.goatTutorats.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VisitRepository extends JpaRepository<Visit, UUID> {

}
