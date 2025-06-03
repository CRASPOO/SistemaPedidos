// src/main/java/com/example/order/application/adapter/out/persistence/OrderEntity.java
package com.example.order.application.adapter.out.persistence;

import com.example.order.domain.model.Order; // Importa a entidade de domínio 'Order'

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity // Marca esta classe como uma entidade JPA
@Table(name = "orderqueue") // Nome da tabela no banco, conforme seu DDL original
public class OrderEntity {
    @Id // Marca o campo 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a geração automática de ID (ex: SERIAL no PostgreSQL)
    private Long id;

    @Column(name = "idcustomer") // Nome da coluna no banco, se for diferente do nome do campo ou para clareza
    private Long idcustomer;

    // @Column(name = "step") // Opcional, se o nome da coluna for o mesmo
    private String step;

    // @Column(name = "date") // Opcional
    private LocalDate date;

    @Column(name = "time") // Explicitamente mapeado para "time", pois pode ser palavra reservada em alguns DBs
    private LocalTime time;

    // @Column(name = "price") // Opcional
    private Integer price;

    // @Column(name = "details") // Opcional
    private String details;

    // Construtor padrão exigido pelo JPA
    public OrderEntity() {
    }

    // Construtor completo para mapeamento do domínio para a entidade JPA
    public OrderEntity(Long id, Long idcustomer, String step, LocalDate date, LocalTime time, Integer price, String details) {
        this.id = id;
        this.idcustomer = idcustomer;
        this.step = step;
        this.date = date;
        this.time = time;
        this.price = price;
        this.details = details;
    }

    /**
     * Método para mapear uma entidade de domínio (Order) para uma entidade JPA (OrderEntity).
     * Usado ao persistir dados no banco.
     * @param domainOrder O objeto de domínio Order.
     * @return Uma nova instância de OrderEntity.
     */
    public static OrderEntity fromDomain(Order domainOrder) {
        // Observação: domainOrder.getId() pode ser nulo para novas entidades que ainda não foram persistidas.
        return new OrderEntity(
                domainOrder.getId(),
                domainOrder.getIdcustomer(),
                domainOrder.getStep(),
                domainOrder.getDate(),
                domainOrder.getTime(),
                domainOrder.getPrice(),
                domainOrder.getDetails()
        );
    }

    /**
     * Método para mapear uma entidade JPA (OrderEntity) para uma entidade de domínio (Order).
     * Usado ao ler dados do banco para a camada de domínio.
     * @return Uma nova instância de Order.
     */
    public Order toDomain() {
        return new Order(
                this.id,
                this.idcustomer,
                this.step,
                this.date,
                this.time,
                this.price,
                this.details
        );
    }

    // --- Getters e Setters (necessários para JPA) ---
    // O JPA usa esses métodos para ler e escrever nos campos da entidade
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdcustomer() { return idcustomer; }
    public void setIdcustomer(Long idcustomer) { this.idcustomer = idcustomer; }
    public String getStep() { return step; }
    public void setStep(String step) { this.step = step; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}