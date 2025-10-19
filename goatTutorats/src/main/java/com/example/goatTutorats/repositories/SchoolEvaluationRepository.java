package com.example.goatTutorats.repositories;
import com.example.goatTutorats.entities.ReportEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolEvaluationRepository extends JpaRepository<ReportEvaluation, UUID> {

}
