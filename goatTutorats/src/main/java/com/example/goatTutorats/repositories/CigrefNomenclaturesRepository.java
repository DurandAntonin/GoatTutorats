package com.example.goatTutorats.repositories;

import com.example.goatTutorats.entities.CigrefNomenclatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CigrefNomenclaturesRepository extends JpaRepository<CigrefNomenclatures, UUID> {
}
