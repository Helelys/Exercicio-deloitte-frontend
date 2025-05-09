package com.example.deloitte.gerenciamento.de.salas.controller;

import com.example.deloitte.gerenciamento.de.salas.model.Sala;
import com.example.deloitte.gerenciamento.de.salas.repository.SalaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
@AllArgsConstructor
public class SalaController {

    private SalaRepository salaRepository;

    @GetMapping("/listar")
    public List<Sala> listarSalas() {
        System.out.println("Lista de salas:");
        return salaRepository.findAll();
    }

    @PostMapping("/adicionar")
    public Sala adicionarSala(@RequestBody Sala sala) {
        System.out.println("Sala adicionada!");
        return salaRepository.save(sala);
    }

    @DeleteMapping("/delete/{id}")
    public void removerSala(@PathVariable Long id) {
        System.out.println("Sala deletada!");
        salaRepository.deleteById(id);
    }
}
