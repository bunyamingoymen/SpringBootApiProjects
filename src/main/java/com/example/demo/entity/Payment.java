package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long clientId;
    private int totalAmount;
    private LocalDate date;

    public Payment() {
    }

    public Payment(Long id, Long clientId, int totalAmount, LocalDate date) {
        this.id = id;
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public Payment(Long clientId, int totalAmount, LocalDate date) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getId().equals(payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
