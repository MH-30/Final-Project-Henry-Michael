package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Shirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private ConsoleRepository consoleRepo;

    @Autowired
    private ShirtRepository shirtRepo;

    @Before
    public void setUp() throws Exception {
        invoiceRepo.deleteAll();
    }

    @Test
    public void testCreateInvoice() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(20.49);
        shirt.setSize("Medium");

        shirt = shirtRepo.save(shirt);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(4);
        invoice.setQuantity(1);
        invoice.setState("CA");
        invoice.setItemId(shirt.getShirtId());
        invoice.setItemType("console");
        invoice.setName("Tim");
        invoice.setZipcode("33331");
        invoice.setStreet("12345");
        invoice.setCity("Davis");
        invoice = invoiceRepo.save(invoice);
        Optional<Invoice> invoiceOptional = invoiceRepo.findById(invoice.getInvoiceId());
        assertEquals(invoiceOptional.get(), invoice);
    }

    @Test
    public void testGetInvoiceById() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");
        game = gameRepo.save(game);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(4);
        invoice.setQuantity(1);
        invoice.setState("CA");
        invoice.setItemId(game.getGameId());
        invoice.setItemType("console");
        invoice.setName("Tim");
        invoice.setZipcode("33331");
        invoice.setStreet("12345");
        invoice.setCity("Davis");
        invoice = invoiceRepo.save(invoice);
        Optional<Invoice> invoiceOptional = invoiceRepo.findById(invoice.getInvoiceId());
        assertEquals(invoiceOptional.get(), invoice);
    }

    @Test
    public void testGetAllInvoices() throws Exception {
            Game game = new Game();
            game.setTitle("AroundTown");
            game.setEsrbRating("solid");
            game.setDescription("Nice game");
            game.setPrice(BigDecimal.valueOf(42.00));
            game.setQuantity(10);
            game.setStudio("Epic");
            game = gameRepo.save(game);

            Shirt shirt = new Shirt();
            shirt.setColor("Green");
            shirt.setDescription("Netflix Swag");
            shirt.setQuantity(44);
            shirt.setPrice(20.49);
            shirt.setSize("Medium");

            shirt = shirtRepo.save(shirt);

            Console console = new Console();
            console.setModel("2015");
            console.setManufacturer("MirrorsInc");
            console.setProcessor("E4");
            console.setMemoryAmount("15GB");
            console.setPrice(135.00);
            console.setQuantity(77);
            Console savedConsole = consoleRepo.save(console);

            Invoice invoice1 = new Invoice();
            invoice1.setInvoiceId(4);
            invoice1.setQuantity(1);
            invoice1.setState("CA");
            invoice1.setItemId(game.getGameId());
            invoice1.setItemType("console");
            invoice1.setName("Tim");
            invoice1.setZipcode("33331");
            invoice1.setStreet("12345");
            invoice1.setCity("Davis");
            invoice1 = invoiceRepo.save(invoice1);

            Invoice invoice2 = new Invoice();
            invoice2.setInvoiceId(4);
            invoice2.setQuantity(1);
            invoice2.setState("CA");
            invoice2.setItemId(console.getConsoleId());
            invoice2.setItemType("console");
            invoice2.setName("Tim");
            invoice2.setZipcode("33331");
            invoice2.setStreet("12345");
            invoice2.setCity("Davis");
            invoice2 = invoiceRepo.save(invoice2);

            Invoice invoice3 = new Invoice();
            invoice3.setInvoiceId(4);
            invoice3.setQuantity(1);
            invoice3.setState("CA");
            invoice3.setItemId(shirt.getShirtId());
            invoice3.setItemType("console");
            invoice3.setName("Tim");
            invoice3.setZipcode("33331");
            invoice3.setStreet("12345");
            invoice3.setCity("Davis");
            invoice3 = invoiceRepo.save(invoice3);

            List<Invoice> invoices = invoiceRepo.findAll();
            assertEquals(3, invoices.size());
    }

    @Test
    public void testGetInvoiceByName() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");
        game = gameRepo.save(game);

        Shirt shirt = new Shirt();
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(20.49);
        shirt.setSize("Medium");

        shirt = shirtRepo.save(shirt);

        Console console = new Console();
        console.setModel("2015");
        console.setManufacturer("MirrorsInc");
        console.setProcessor("E4");
        console.setMemoryAmount("15GB");
        console.setPrice(135.00);
        console.setQuantity(77);
        Console savedConsole = consoleRepo.save(console);

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(4);
        invoice1.setQuantity(1);
        invoice1.setState("CA");
        invoice1.setItemId(game.getGameId());
        invoice1.setItemType("console");
        invoice1.setName("Tim");
        invoice1.setZipcode("33331");
        invoice1.setStreet("12345");
        invoice1.setCity("Davis");
        invoice1 = invoiceRepo.save(invoice1);

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(4);
        invoice2.setQuantity(1);
        invoice2.setState("CA");
        invoice2.setItemId(console.getConsoleId());
        invoice2.setItemType("console");
        invoice2.setName("John");
        invoice2.setZipcode("33331");
        invoice2.setStreet("12345");
        invoice2.setCity("Davis");
        invoice2 = invoiceRepo.save(invoice2);

        Invoice invoice3 = new Invoice();
        invoice3.setInvoiceId(4);
        invoice3.setQuantity(1);
        invoice3.setState("CA");
        invoice3.setItemId(shirt.getShirtId());
        invoice3.setItemType("console");
        invoice3.setName("Tim");
        invoice3.setZipcode("33331");
        invoice3.setStreet("12345");
        invoice3.setCity("Davis");
        invoice3 = invoiceRepo.save(invoice3);

        assertEquals(2, invoiceRepo.findByName("Tim").size());
        assertEquals(1, invoiceRepo.findByName("John").size());
        assertEquals(3, invoiceRepo.findAll().size());
    }

}