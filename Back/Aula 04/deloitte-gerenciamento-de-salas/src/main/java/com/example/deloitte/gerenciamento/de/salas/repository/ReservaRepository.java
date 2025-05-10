package com.example.deloitte.gerenciamento.de.salas.repository;

import com.example.deloitte.gerenciamento.de.salas.model.Reserva;
import com.example.deloitte.gerenciamento.de.salas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.sala = :sala AND r.inicio >= :now ORDER BY r.inicio")
    List<Reserva> procurarReservasFuturasPorSala(@Param("sala") Sala sala, @Param("now") LocalDateTime now);

    @Query("SELECT r FROM Reserva r WHERE r.inicio >= :now ORDER BY r.inicio")
    List<Reserva> procurarReservasFuturas(@Param("now") LocalDateTime now);

    @Query("""
        SELECT COUNT(r) > 0 FROM Reserva r 
        WHERE r.sala = :sala AND (
            (r.inicio BETWEEN :inicioTempo AND :terminoTempo) OR 
            (r.termino BETWEEN :inicioTempo AND :terminoTempo) OR 
            (:inicioTempo BETWEEN r.inicio AND r.termino)
        )
        """)
    boolean temReservaSobreposta(
            @Param("sala") Sala sala,
            @Param("inicioTempo") LocalDateTime inicioTempo,
            @Param("terminoTempo") LocalDateTime terminoTempo
    );
}
