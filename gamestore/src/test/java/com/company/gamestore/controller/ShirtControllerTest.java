package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Shirt;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.ShirtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShirtController.class)
public class ShirtControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShirtRepository shirtRepo;

    @Test
    public void testCreateShirt() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(BigDecimal.valueOf(20.49));
        shirt.setSize("Medium");

        String inputJson = objectMapper.writeValueAsString(shirt);
        mockMvc.perform(MockMvcRequestBuilders.post("/shirt").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteShirt() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(BigDecimal.valueOf(20.49));
        shirt.setSize("Medium");
        shirt.setShirtId(1);
        shirtRepo.save(shirt);
        shirtRepo.deleteById(shirt.getShirtId());
        mockMvc.perform(delete("/shirt/1"))
                .andExpect(status().isNoContent());
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
        shirtRepo.save(shirt);

        shirt.setColor("Red");
        shirt.setDescription("Amazon Swag");
        shirt.setQuantity(43);
        shirt.setPrice(BigDecimal.valueOf(20.42));
        shirt.setSize("Small");

        shirtRepo.save(shirt);

        String inputJson = objectMapper.writeValueAsString(shirt);

        mockMvc.perform(put("/shirt/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetShirtById() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setShirtId(1);
        shirt.setColor("Green");
        shirt.setDescription("Netflix Swag");
        shirt.setQuantity(44);
        shirt.setPrice(BigDecimal.valueOf(20.49));
        shirt.setSize("Medium");
        shirtRepo.save(shirt);
        String inputJson = objectMapper.writeValueAsString(shirt);
        mockMvc.perform(MockMvcRequestBuilders.get("/shirt/1")).andDo(print()).andExpect(status().isOk());
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

        mockMvc.perform(MockMvcRequestBuilders.get("/shirt/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetShirtByColor() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Blue");
        shirt.setDescription("Google Swag");
        shirt.setQuantity(78);
        shirt.setPrice(BigDecimal.valueOf(23.70));
        shirt.setSize("Large");

        shirtRepo.save(shirt);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/shirt/color/Blue")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetShirtBySize() throws Exception {
        Shirt shirt = new Shirt();
        shirt.setColor("Blue");
        shirt.setDescription("Google Swag");
        shirt.setQuantity(78);
        shirt.setPrice(BigDecimal.valueOf(23.70));
        shirt.setSize("Large");

        shirtRepo.save(shirt);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/shirt/size/Large")
                .contentType(MediaType.APPLICATION_JSON));

    }
}