package com.example.deloitte.gerenciamento.de.salas.controller;

import com.example.deloitte.gerenciamento.de.salas.model.Reserva;
import com.example.deloitte.gerenciamento.de.salas.repository.ReservaRepository;
import com.example.deloitte.gerenciamento.de.salas.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> obterFuturasReservas() {
        return ResponseEntity.ok(reservaService.terFuturasReservas());
    }

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        return new ResponseEntity<>(reservaService.criarReserva(reserva), HttpStatus.CREATED);
    }

    @GetMapping("/sala/{id}")
    public ResponseEntity<List<Reserva>> terReservaPorIdDeSala(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.terFuturasReservasPorId(id));
    }

    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
