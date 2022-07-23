package com.example.demo.controller;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.Payment;
import com.example.demo.servies.InvoiceServices;
import com.example.demo.servies.PaymentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequestScope
public class PaymentController {

    private final PaymentServices paymentServices;
    private final InvoiceServices invoiceServices;

    public PaymentController(PaymentServices paymentServices, InvoiceServices invoiceServices) {
        this.paymentServices = paymentServices;
        this.invoiceServices = invoiceServices;
    }

    @GetMapping("/get")
    public List<Payment> getAll(){
        return this.paymentServices.getPayments();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.paymentServices.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(404).body("Invoice Not Found: " + e.getMessage());
        }
    }

    @PutMapping("/pay/{id}")
    public  ResponseEntity<?> pay(@PathVariable("id") Long id){
        try {

            this.paymentServices.update(( (-1) * this.paymentServices.findById(id).getTotalAmount()), paymentServices.findById(id).getClientId());

            List<Invoice> list = this.invoiceServices.findByClientId(this.paymentServices.findById(id).getClientId());

            for(Invoice value : list)
                this.invoiceServices.pay(value.getId());

            return ResponseEntity.ok("Invoice Payed");

        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
