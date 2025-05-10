package com.example.deloitte.gerenciamento.de.salas.excecoes;

public class InvalidReservationException extends RuntimeException {
    public InvalidReservationException(String message) {
        super(message);
    }
}
