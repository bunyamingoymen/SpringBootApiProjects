package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // fatura_no

    private Long clientId;

    private int total;

    private LocalDate transactionDate;

    private boolean isPay;

    public Invoice() {
    }

    public Invoice(Long id, Long clientId, int total, LocalDate transactionDate, boolean isPay) {
        this.id = id;
        this.clientId = clientId;
        this.total = total;
        this.transactionDate = transactionDate;
        this.isPay = isPay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return getId().equals(invoice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
