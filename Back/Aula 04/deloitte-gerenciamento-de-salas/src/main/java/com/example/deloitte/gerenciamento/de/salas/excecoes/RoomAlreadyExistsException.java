package com.example.deloitte.gerenciamento.de.salas.excecoes;

public class RoomAlreadyExistsException extends RuntimeException {
    public RoomAlreadyExistsException(String message) {
        super(message);
    }
}
