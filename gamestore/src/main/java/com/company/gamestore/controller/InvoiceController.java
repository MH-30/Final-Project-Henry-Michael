package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.repository.ShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepo;

    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    // Read by Id
    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoicceById(@PathVariable Integer id){
        Optional<Invoice> returnInvoice = invoiceRepo.findById(id);
        if (returnInvoice.isPresent()) return returnInvoice.get();
        return null;
    }

    @GetMapping("/invoice/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices(){
        return invoiceRepo.findAll();
    }

    @GetMapping("/invoice/name/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getInvoiceByName(@PathVariable String name) {
        return invoiceRepo.findByName(name);
    }

}
