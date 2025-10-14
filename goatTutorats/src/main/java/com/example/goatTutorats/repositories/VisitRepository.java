package com.example.goatTutorats.repositories;
import com.example.goatTutorats.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Tutor,Integer> {

}
