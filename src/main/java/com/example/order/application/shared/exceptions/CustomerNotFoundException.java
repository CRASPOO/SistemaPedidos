package com.example.order.shared.exceptions;

// Uma exceção de domínio, usada pelo serviço de domínio
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}