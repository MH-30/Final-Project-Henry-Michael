package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Shirt;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InvoiceRepository invoiceRepo;

    @Test
    public void testCreateInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setTotal(BigDecimal.valueOf(34));
        invoice.setUnitPrice(BigDecimal.valueOf(34));
        invoice.setName("Tim");
        invoice.setZipcode("33331");
        invoice.setStreet("12345");
        invoice.setCity("Davis");
        invoice.setQuantity(33);

        String inputJson = objectMapper.writeValueAsString(invoice);
        mockMvc.perform(MockMvcRequestBuilders.post("/invoice").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetInvoiceById() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setTotal(BigDecimal.valueOf(34));
        invoice.setUnitPrice(BigDecimal.valueOf(34));
        invoice.setName("Tim");
        invoice.setZipcode("33331");
        invoice.setStreet("12345");
        invoice.setCity("Davis");
        invoice.setQuantity(33);
        invoice.setInvoiceId(1);
        invoiceRepo.save(invoice);
        String inputJson = objectMapper.writeValueAsString(invoice);
        mockMvc.perform(MockMvcRequestBuilders.get("/invoice/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetAllInvoices() throws Exception {
        Invoice invoice1 = new Invoice();
        invoice1.setTotal(BigDecimal.valueOf(34));
        invoice1.setUnitPrice(BigDecimal.valueOf(34));
        invoice1.setName("Tim");
        invoice1.setZipcode("33331");
        invoice1.setStreet("12345");
        invoice1.setCity("Davis");
        invoice1.setQuantity(33);

        Invoice invoice2 = new Invoice();
        invoice2.setTotal(BigDecimal.valueOf(34));
        invoice2.setUnitPrice(BigDecimal.valueOf(34));
        invoice2.setName("Jonah");
        invoice2.setZipcode("00000");
        invoice2.setStreet("54321");
        invoice2.setCity("Davis");
        invoice2.setQuantity(33);

        Invoice invoice3 = new Invoice();
        invoice3.setTotal(BigDecimal.valueOf(34));
        invoice3.setUnitPrice(BigDecimal.valueOf(34));
        invoice3.setName("Sally");
        invoice3.setZipcode("00000");
        invoice3.setStreet("54321");
        invoice3.setCity("Davis");
        invoice3.setQuantity(33);

        invoiceRepo.save(invoice1);
        invoiceRepo.save(invoice2);
        invoiceRepo.save(invoice3);

        mockMvc.perform(MockMvcRequestBuilders.get("/invoice/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInvoiceByName() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setTotal(BigDecimal.valueOf(34));
        invoice.setUnitPrice(BigDecimal.valueOf(34));
        invoice.setName("Tim");
        invoice.setZipcode("33331");
        invoice.setStreet("12345");
        invoice.setCity("Davis");
        invoice.setQuantity(33);
        invoiceRepo.save(invoice);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/invoice/name/Tim")
                .contentType(MediaType.APPLICATION_JSON));
    }
}