package com.example.order.shared.exceptions;

// Uma exceção de domínio, usada pelo serviço de domínio
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}