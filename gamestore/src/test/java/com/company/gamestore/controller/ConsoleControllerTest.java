package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConsoleRepository consoleRepo;

    @Test
    public void testCreateConsole() throws Exception {
        Console console = new Console();
        console.setModel("2015");
        console.setManufacturer("MirrorsInc");
        console.setProcessor("E4");
        console.setMemoryAmount("15GB");
        console.setPrice(135.00);
        console.setQuantity(77);

        consoleRepo.save(console);

        String inputJson = objectMapper.writeValueAsString(console);
        mockMvc.perform(MockMvcRequestBuilders.post("/console").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void testGetConsoleById() throws Exception {
        Console console = new Console();
        console.setModel("2015");
        console.setManufacturer("MirrorsInc");
        console.setProcessor("E4");
        console.setMemoryAmount("15GB");
        console.setPrice(135.00);
        console.setQuantity(77);
        console.setConsoleId(1);
        consoleRepo.save(console);

        String inputJson = objectMapper.writeValueAsString(console);
        mockMvc.perform(MockMvcRequestBuilders.get("/console/1")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void testUpdateConsoleById() throws Exception {
        Console console = new Console();
        console.setModel("2015");
        console.setManufacturer("MirrorsInc");
        console.setProcessor("E4");
        console.setMemoryAmount("15GB");
        console.setPrice(135.00);
        console.setQuantity(77);
        console.setConsoleId(1);
        consoleRepo.save(console);

        console.setModel("2016");
        console.setManufacturer("BlindsInc");
        console.setProcessor("D5");
        console.setMemoryAmount("22GB");
        console.setPrice(177.00);
        console.setQuantity(35);

        consoleRepo.save(console);

        String inputJson = objectMapper.writeValueAsString(console);

        mockMvc.perform(put("/console/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void testDeleteConsole() throws Exception {
        Console console = new Console();
        console.setModel("2015");
        console.setManufacturer("MirrorsInc");
        console.setProcessor("E4");
        console.setMemoryAmount("15GB");
        console.setPrice(135.00);
        console.setQuantity(77);
        console.setConsoleId(1);
        consoleRepo.save(console);

        consoleRepo.deleteById(console.getConsoleId());
        mockMvc.perform(delete("/console/1"))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testGetAllConsoles() throws Exception {
        Console console1 = new Console();
        console1.setModel("2015");
        console1.setManufacturer("MirrorsInc");
        console1.setProcessor("E4");
        console1.setMemoryAmount("15GB");
        console1.setPrice(135.00);
        console1.setQuantity(77);

        Console console2 = new Console();
        console2.setModel("2016");
        console2.setManufacturer("BlindsInc");
        console2.setProcessor("D5");
        console2.setMemoryAmount("22GB");
        console2.setPrice(177.00);
        console2.setQuantity(35);

        Console console3 = new Console();
        console3.setModel("2017");
        console3.setManufacturer("TiresInc");
        console3.setProcessor("G6");
        console3.setMemoryAmount("44GB");
        console3.setPrice(900.00);
        console3.setQuantity(2005);

        consoleRepo.save(console1);
        consoleRepo.save(console2);
        consoleRepo.save(console3);

        mockMvc.perform(MockMvcRequestBuilders.get("/console/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetConsoleByManufacturer() throws Exception {
        Console console = new Console();
        console.setModel("2015");
        console.setManufacturer("MirrorsInc");
        console.setProcessor("E4");
        console.setMemoryAmount("15GB");
        console.setPrice(135.00);
        console.setQuantity(77);

        consoleRepo.save(console);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/console/manufacturer/MirrorsInc")
                .contentType(MediaType.APPLICATION_JSON));
    }
}