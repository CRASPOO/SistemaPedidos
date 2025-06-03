// src/main/java/com/example/order/application/adapter/out/persistence/JpaOrderRepositoryAdapter.java
package com.example.order.application.adapter.out.persistence;

import com.example.order.domain.model.Order; // Importe o modelo de domínio
import com.example.order.domain.port.out.OrderRepositoryPort; // Sua porta de saída
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // Necessário para coletar em listas

@Component // Indispensável para o Spring encontrar e injetar
public class JpaOrderRepositoryAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository springDataOrderRepository;

    public JpaOrderRepositoryAdapter(SpringDataOrderRepository springDataOrderRepository) {
        this.springDataOrderRepository = springDataOrderRepository;
    }

    @Override
    public List<Order> findAll() {
        // O repositório Spring Data JPA retorna List<OrderEntity>.
        // Precisamos mapeá-las para List<Order> antes de retornar ao domínio.
        return springDataOrderRepository.findAll().stream()
                .map(OrderEntity::toDomain) // Converte cada OrderEntity para Order
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(Long id) {
        // O repositório Spring Data JPA retorna Optional<OrderEntity>.
        // Usamos .map() para converter a OrderEntity (se presente) para Order.
        return springDataOrderRepository.findById(id)
                .map(OrderEntity::toDomain); // Converte OrderEntity para Order dentro do Optional
    }

    @Override
    public List<Order> findAllByStep(String step) {
        // Semelhante ao findAll(), mas com filtro.
        return springDataOrderRepository.findAllByStep(step).stream()
                .map(OrderEntity::toDomain) // Converte cada OrderEntity para Order
                .collect(Collectors.toList());
    }

    @Override
    public Order save(Order order) {
        // Antes de salvar, convertemos o objeto de domínio (Order) para a entidade JPA (OrderEntity).
        OrderEntity orderEntity = OrderEntity.fromDomain(order);

        // Salvamos a entidade JPA no banco de dados.
        OrderEntity savedEntity = springDataOrderRepository.save(orderEntity);

        // Após salvar, convertemos a entidade salva de volta para o objeto de domínio
        // (importante para capturar o ID gerado pelo banco de dados para novas entidades).
        return savedEntity.toDomain();
    }
}