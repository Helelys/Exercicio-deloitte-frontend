package com.example.deloitte.gerenciamento.de.salas.service;

import com.example.deloitte.gerenciamento.de.salas.excecoes.InvalidReservationException;
import com.example.deloitte.gerenciamento.de.salas.excecoes.ResourceNotFoundException;
import com.example.deloitte.gerenciamento.de.salas.model.Reserva;
import com.example.deloitte.gerenciamento.de.salas.model.Sala;
import com.example.deloitte.gerenciamento.de.salas.repository.ReservaRepository;
import com.example.deloitte.gerenciamento.de.salas.repository.SalaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final SalaService salaService;

    @Transactional
    public Reserva criarReserva(Reserva reserva) {
        validarReserva(reserva);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> terFuturasReservas() {
        return reservaRepository.procurarReservasFuturas(LocalDateTime.now());
    }

    public List<Reserva> terFuturasReservasPorId(Long id) {
        Sala sala = salaService.salaPorId(id);
        return reservaRepository.procurarReservasFuturasPorSala(sala, LocalDateTime.now());
    }

    @Transactional
    public void cancelarReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reserva não encontrada com o id: " + id);
        }
        reservaRepository.deleteById(id);
    }

    private void validarReserva(Reserva reserva) {
        LocalDateTime now = LocalDateTime.now();
        if (reserva.getInicio().isBefore(now)) {
            throw new InvalidReservationException("Não é possível fazer reservas para datas/horários passados.");
        }
        if (reserva.getTermino().isBefore(reserva.getInicio())) {
            throw new InvalidReservationException("A data de término não pode ser posterior a hora de início");
        }
        if (reservaRepository.temReservaSobreposta(
                reserva.getSala(),
                reserva.getInicio(),
                reserva.getTermino())) {
            throw new InvalidReservationException("A sala já está reservada para esse período");
        }
    }
}
