package com.example.demo.servies;

import com.example.demo.entity.Payment;
import com.example.demo.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServices {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServices(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getPayments(){
        return paymentRepository.findAll();
    }

    public void createPayment(Payment toAdd){
        paymentRepository.save(toAdd);
        System.out.println("Added: " + toAdd.getId());
    }

    public final Payment findById(Long id){
        System.out.println("Find yeni: " + id);
        return paymentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Payment Not Found"));
    }

    public void delete(Long clientId){
        paymentRepository.deleteById(paymentRepository.findByClientId(clientId).get(0).getId());
        System.out.println("Payment Deleted Client id: " + clientId);
    }

    @Transactional
    public void update(int amount, Long clientId){
        Payment payment = paymentRepository.findByClientId(clientId).get(0);

        payment.setTotalAmount(payment.getTotalAmount() + amount);
        payment.setDate(LocalDate.now());

    }

    @Transactional
    public void update(int oldAmount,int newAmount, Long clientId){

        try {
            Payment payment = paymentRepository.findByClientId(clientId).get(0);

            payment.setTotalAmount(payment.getTotalAmount() - oldAmount + newAmount);
            payment.setDate(LocalDate.now());

        }catch (Exception e){
            System.out.println("Hata: " +  e.getMessage());
        }

    }
}
