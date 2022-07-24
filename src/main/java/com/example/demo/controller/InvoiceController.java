package com.example.demo.controller;

import com.example.demo.entity.Invoice;
import com.example.demo.servies.InvoiceServices;
import com.example.demo.servies.PaymentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/invoice")
@RequestScope
public class InvoiceController {
    private final InvoiceServices invoiceServices;
    private final PaymentServices paymentServices;

    public InvoiceController(InvoiceServices invoiceServices, PaymentServices paymentServices) {
        this.invoiceServices = invoiceServices;
        this.paymentServices = paymentServices;
    }

    @GetMapping("/get")
    public List<Invoice> getAll(){
        try {
            return this.invoiceServices.getInvoices();
        }catch (Exception e){
            System.out.println("Hatalı: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<?> getById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.invoiceServices.findById(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/clientid/{clientId}")
    public  ResponseEntity<?> getByClientId(@PathVariable("clientId") Long clientId){
        try {
            return ResponseEntity.ok(this.invoiceServices.findByClientId(clientId));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createInvoice")
    public ResponseEntity<?> add(@RequestBody Invoice in){
        this.invoiceServices.createInvoice(in);
        paymentServices.update(in.getTotal(),in.getClientId());
        return ResponseEntity.ok("Invoice Created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            this.paymentServices.update(( (-1) * this.invoiceServices.findById(id).getTotal()), invoiceServices.findById(id).getClientId());
            this.invoiceServices.delete(id);
            return ResponseEntity.ok("Invoice Deleted");
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> update(@RequestBody Invoice invoice, @PathVariable("id") Long id){
        try {

            if(Objects.nonNull(invoice.getTotal())){
                this.paymentServices.update(invoiceServices.findById(id).getTotal(), invoice.getTotal(), invoiceServices.findById(id).getClientId());
            }
            this.invoiceServices.update(invoice,id);
            return ResponseEntity.ok("Invoice Updated");
        }catch (Exception e) {
            return ResponseEntity.status(404).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/pay/{id}")
    public  ResponseEntity<?> pay(@PathVariable("id") Long id){
        try {
            this.paymentServices.update(( (-1) * this.invoiceServices.findById(id).getTotal()), invoiceServices.findById(id).getClientId());
            this.invoiceServices.pay(id);
            return ResponseEntity.ok("Invoice Payed");
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
