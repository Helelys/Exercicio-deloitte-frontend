package com.example.deloitte.gerenciamento.de.salas.excecoes;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
