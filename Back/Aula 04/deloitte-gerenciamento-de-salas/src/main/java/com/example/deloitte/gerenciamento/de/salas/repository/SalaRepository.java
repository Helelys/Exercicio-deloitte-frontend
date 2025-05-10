package com.example.deloitte.gerenciamento.de.salas.repository;

import com.example.deloitte.gerenciamento.de.salas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    boolean existsByNome(String nome);
    Optional<Sala> findByNome(String nome);
}
