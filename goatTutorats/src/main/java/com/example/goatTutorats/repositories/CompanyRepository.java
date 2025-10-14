package com.example.goatTutorats.repositories;
import com.example.goatTutorats.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

}
