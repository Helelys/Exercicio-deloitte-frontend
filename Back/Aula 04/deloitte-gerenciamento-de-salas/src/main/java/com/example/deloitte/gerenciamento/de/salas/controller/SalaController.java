package com.example.deloitte.gerenciamento.de.salas.controller;

import com.example.deloitte.gerenciamento.de.salas.model.Sala;
import com.example.deloitte.gerenciamento.de.salas.service.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @GetMapping
    public ResponseEntity<List<Sala>> terTodasAsSalas() {
        return ResponseEntity.ok(salaService.todasAsSalas());
    }

    @PostMapping
    public ResponseEntity<Sala> criarSala(@RequestBody Sala sala) {
        return new ResponseEntity<>(salaService.criarSala(sala), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> terSalaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.salaPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();
    }

}
