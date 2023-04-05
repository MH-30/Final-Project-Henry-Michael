package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShirtRepositoryTest {

    @Autowired
    private ShirtRepository shirtRepo;

    @Before
    public void setUp() throws Exception {
        shirtRepo.deleteAll();
    }

    @Test
    public void testCreateShirt() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(BigDecimal.valueOf(20.49));
        shirt.setSize("Medium");

        shirt = shirtRepo.save(shirt);
        Optional<Shirt> shirtOptional = shirtRepo.findById(shirt.getShirtId());
        assertEquals(shirtOptional.get(), shirt);
    }

    @Test
    public void testDeleteShirt() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(BigDecimal.valueOf(20.49));
        shirt.setSize("Medium");
        shirt = shirtRepo.save(shirt);

        shirtRepo.deleteById(shirt.getShirtId());

        Optional<Shirt> deletedShirt = shirtRepo.findById(shirt.getShirtId());
        assertFalse(deletedShirt.isPresent());

    }

    @Test
    public void testUpdateShirt() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(BigDecimal.valueOf(20.49));
        shirt.setSize("Medium");
        shirt.setShirtId(1);
        shirt = shirtRepo.save(shirt);

        shirt.setColor("Red");
        shirt.setDescription("Amazon Swag");
        shirt.setQuantity(43);
        shirt.setPrice(BigDecimal.valueOf(20.42));
        shirt.setSize("Small");

        shirt = shirtRepo.save(shirt);
        Optional<Shirt> shirtOptional = shirtRepo.findById(shirt.getShirtId());
        assertEquals(shirtOptional.get(), shirt);

    }

    @Test
    public void testGetShirtById() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(BigDecimal.valueOf(20.49));
        shirt.setSize("Medium");
        shirt = shirtRepo.save(shirt);
        Optional<Shirt> shirtOptional = shirtRepo.findById(shirt.getShirtId());
        assertEquals(shirtOptional.get(), shirt);

    }

    @Test
    public void testGetAllShirts() throws Exception {
        Shirt shirt1 = new Shirt();
        shirt1.setColor("Green");
        shirt1.setDescription("Netflix Swag");
        shirt1.setQuantity(44);
        shirt1.setPrice(BigDecimal.valueOf(20.49));
        shirt1.setSize("Medium");

        Shirt shirt2 = new Shirt();
        shirt2.setColor("Red");
        shirt2.setDescription("Amazon Swag");
        shirt2.setQuantity(43);
        shirt2.setPrice(BigDecimal.valueOf(20.42));
        shirt2.setSize("Small");

        Shirt shirt3 = new Shirt();
        shirt3.setColor("Blue");
        shirt3.setDescription("Google Swag");
        shirt3.setQuantity(78);
        shirt3.setPrice(BigDecimal.valueOf(23.70));
        shirt3.setSize("Large");

        shirtRepo.save(shirt1);
        shirtRepo.save(shirt2);
        shirtRepo.save(shirt3);
        List<Shirt> shirts = shirtRepo.findAll();
        assertEquals(3, shirts.size());

    }

    @Test
    public void testGetShirtByColor() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Blue");
        shirt.setDescription("Google Swag");
        shirt.setQuantity(78);
        shirt.setPrice(BigDecimal.valueOf(23.70));
        shirt.setSize("Large");
        shirt = shirtRepo.save(shirt);

        assertEquals(1, shirtRepo.findByColor("Blue").size());

        Shirt shirt2 = new Shirt();
        shirt2.setColor("Red");
        shirt2.setDescription("Amazon Swag");
        shirt2.setQuantity(43);
        shirt2.setPrice(BigDecimal.valueOf(20.42));
        shirt2.setSize("Small");

        shirt2 = shirtRepo.save(shirt2);

        Shirt shirt3 = new Shirt();
        shirt3.setColor("Blue");
        shirt3.setDescription("Google Swag");
        shirt3.setQuantity(78);
        shirt3.setPrice(BigDecimal.valueOf(23.70));
        shirt3.setSize("Large");

        shirt3 = shirtRepo.save(shirt3);

        assertEquals(2, shirtRepo.findByColor("Blue").size());
        assertEquals(1, shirtRepo.findByColor("Red").size());
        assertEquals(3, shirtRepo.findAll().size());

    }

    @Test
    public void testGetShirtBySize() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Blue");
        shirt.setDescription("Google Swag");
        shirt.setQuantity(78);
        shirt.setPrice(BigDecimal.valueOf(23.70));
        shirt.setSize("Large");
        shirt = shirtRepo.save(shirt);

        assertEquals(1, shirtRepo.findBySize("Large").size());

        Shirt shirt2 = new Shirt();
        shirt2.setColor("Red");
        shirt2.setDescription("Amazon Swag");
        shirt2.setQuantity(43);
        shirt2.setPrice(BigDecimal.valueOf(20.42));
        shirt2.setSize("Small");

        shirt2 = shirtRepo.save(shirt2);

        Shirt shirt3 = new Shirt();
        shirt3.setColor("Blue");
        shirt3.setDescription("Google Swag");
        shirt3.setQuantity(78);
        shirt3.setPrice(BigDecimal.valueOf(23.70));
        shirt3.setSize("Large");

        shirt3 = shirtRepo.save(shirt3);

        assertEquals(2, shirtRepo.findBySize("Large").size());
        assertEquals(1, shirtRepo.findBySize("Small").size());
        assertEquals(3, shirtRepo.findAll().size());

    }

}