package com.example.deloitte.gerenciamento.de.salas.repository;

import com.example.deloitte.gerenciamento.de.salas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
}
