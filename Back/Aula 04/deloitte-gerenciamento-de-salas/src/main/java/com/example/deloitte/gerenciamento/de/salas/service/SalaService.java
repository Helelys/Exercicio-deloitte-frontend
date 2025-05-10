package com.example.deloitte.gerenciamento.de.salas.service;

import com.example.deloitte.gerenciamento.de.salas.excecoes.ResourceNotFoundException;
import com.example.deloitte.gerenciamento.de.salas.excecoes.RoomAlreadyExistsException;
import com.example.deloitte.gerenciamento.de.salas.model.Sala;
import com.example.deloitte.gerenciamento.de.salas.repository.SalaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    @Transactional
    public Sala criarSala(Sala sala) {
        try {
            if (salaRepository.existsByNome(sala.getNome())) {
                throw new RoomAlreadyExistsException("Já existe uma sala com esse nome: " + sala.getNome());
            }
            return salaRepository.save(sala);

        } catch (RoomAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Sala> todasAsSalas(){
        return salaRepository.findAll();
    }

    public Sala salaPorId(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada com id: " + id));
    }

    @Transactional
    public Sala atualizarSala(Long id, Sala salaDetalhes) {
        Sala sala = salaPorId(id);
        if (!sala.getNome().equals(salaDetalhes.getNome()) && salaRepository.existsByNome(salaDetalhes.getNome())) {
            throw new RoomAlreadyExistsException("Já existe uma sala com esse nome: " + salaDetalhes.getNome());
        }

        sala.setNome(sala.getNome());
        sala.setCapacidadeMaxima(salaDetalhes.getCapacidadeMaxima());
        sala.setLocalizacao(salaDetalhes.getLocalizacao());
        return salaRepository.save(sala);
    }

    @Transactional
    public void deletarSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sala não encontrada com id: " + id);
        }
        salaRepository.deleteById(id);
    }
}
