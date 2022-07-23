package com.example.demo.controller;

import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.servies.InvoiceServices;
import com.example.demo.servies.PaymentServices;
import com.example.demo.servies.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequestScope
public class UserController {

    private final UserServices userServices;
    private final PaymentServices paymentServices;
    private  final InvoiceServices invoiceServices;

    public UserController(UserServices userServices, PaymentServices paymentServices, InvoiceServices invoiceServices) {
        this.userServices = userServices;
        this.paymentServices = paymentServices;
        this.invoiceServices = invoiceServices;
    }

    @GetMapping("/get")
    public List<User> getAll(){
        return this.userServices.getUsers();
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> add(@RequestBody User user){
        this.userServices.createUser(user);

        Payment payment = new Payment(user.getId(), 0, LocalDate.now());
        this.paymentServices.createPayment(payment);

        return ResponseEntity.ok("User Created");
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<?> getById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.userServices.findById(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            this.paymentServices.delete(id);
            this.invoiceServices.deleteByClientId(id);
            this.userServices.delete(id);
            return ResponseEntity.ok("User Deleted");
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> update(@RequestBody User user, @PathVariable("id") Long id){
        try {
            this.userServices.update(user,id);
            return ResponseEntity.ok("User updated");
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
