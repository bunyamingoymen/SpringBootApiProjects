package com.example.demo.repo;

import com.example.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment,Long> {
    List<Payment> findByClientId(Long clientId);
}

