package com.example.demo.servies;

import com.example.demo.entity.Invoice;
import com.example.demo.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceServices {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServices(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoices(){
        return invoiceRepository.findAll();
    }

    public Invoice findById(Long id){
        return this.invoiceRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Invoice Not Found"));
    }

    public List<Invoice> findByClientId(Long clientId){
        return this.invoiceRepository.findByClientId(clientId);
    }

    public void createInvoice(Invoice toAdd){
        invoiceRepository.save(toAdd);
    }

    public void delete(Long id){
        invoiceRepository.deleteById(id);
        System.out.println("Invoice Deleted: " + id);
    }

    public void deleteByClientId(Long clientId){
        List<Invoice> list = invoiceRepository.findByClientId(clientId);
        for(Invoice value : list)
            invoiceRepository.deleteById(value.getId());
        System.out.println("Invoices Deleted By client id: " + clientId);
    }

    @Transactional
    public void update(Invoice invoice, Long id){

        Invoice invoice1 = this.invoiceRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Invoice Not Found"));

        if(Objects.nonNull(invoice.getClientId()) && invoice.getClientId() > 0){
            invoice1.setClientId(invoice.getClientId());
        }

        if(Objects.nonNull(invoice.getTotal()) && invoice.getTotal()>0){
            invoice1.setTotal(invoice.getTotal());
        }

        if(Objects.nonNull(invoice.getTransactionDate()) && invoice.getTransactionDate().isBefore(LocalDate.now())){
            invoice1.setTransactionDate(invoice.getTransactionDate());
        }

        if(Objects.nonNull(invoice.isPay())){
            invoice1.setPay(invoice.isPay());
        }

    }
    @Transactional
    public void pay(Long id){
        Invoice invoice = this.invoiceRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Invoice Not Found"));
        invoice.setPay(true);

    }



}
