// src/main/java/com/example/order/application/adapter/in/web/OrderController.java
package com.example.order.application.adapter.in.web;

import com.example.order.domain.model.Order; // Importe o modelo de domínio
import com.example.order.domain.port.in.OrderUseCase;
import com.example.order.application.shared.OrderRequestDTO;
import com.example.order.application.shared.OrderResponseDTO;
import com.example.order.application.shared.exceptions.OrderNotFoundException; // Sua exceção de domínio

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
// @CrossOrigin(origins = "*", allowedHeaders = "*") // Mover para configuração global
public class OrderController {

    private final OrderUseCase orderUseCase; // Injeta a porta de entrada do domínio

    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderUseCase.getAllOrders(); // Retorna o modelo de domínio
        return ResponseEntity.ok(orders.stream().map(OrderResponseDTO::fromDomain).collect(Collectors.toList()));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/step/{step}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByStep(@PathVariable String step) {
        List<Order> orders = orderUseCase.getOrdersByStep(step); // Retorna o modelo de domínio
        return ResponseEntity.ok(orders.stream().map(OrderResponseDTO::fromDomain).collect(Collectors.toList()));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO data) {
        // Mapeia DTO de requisição para o modelo de domínio
        Order newOrder = data.toDomain();
        Order createdOrder = orderUseCase.createOrder(newOrder); // Passa o modelo de domínio
        // Mapeia o modelo de domínio resultante para DTO de resposta
        return OrderResponseDTO.fromDomain(createdOrder);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/next/{id}")
    public ResponseEntity<OrderResponseDTO> advanceStep(@PathVariable Long id) {
        try {
            Order updatedOrder = orderUseCase.advanceOrderStatus(id); // Retorna o modelo de domínio
            return ResponseEntity.ok(OrderResponseDTO.fromDomain(updatedOrder));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (IllegalArgumentException e) {
            // Exceção lançada pelo método advanceStatus no modelo Order
            return ResponseEntity.badRequest().body(new OrderResponseDTO(id, e.getMessage())); // 400 Bad Request
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long id) {
        try {
            Order canceledOrder = orderUseCase.cancelOrder(id); // Retorna o modelo de domínio
            return ResponseEntity.ok(OrderResponseDTO.fromDomain(canceledOrder));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}