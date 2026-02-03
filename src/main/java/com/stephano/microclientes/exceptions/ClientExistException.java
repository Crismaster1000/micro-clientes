package com.stephano.microclientes.exceptions;

public class ClientExistException extends RuntimeException {
    public ClientExistException() {
        super("Cliente ya existe");
    }
}
