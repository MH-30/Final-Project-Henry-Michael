package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    private ConsoleRepository consoleRepo;

    @Before
    public void setUp() throws Exception {
        consoleRepo.deleteAll();
    }

    @Test
    public void testCreateConsole() throws Exception {
        Console console = new Console();
        console.setModel("2015");
        console.setManufacturer("MirrorsInc");
        console.setProcessor("E4");
        console.setMemoryAmount("15GB");
        console.setPrice(135.00);
        console.setQuantity(77);
        // Convert author object into Json
        console = consoleRepo.save(console);
        Optional<Console> consoleOptional = consoleRepo.findById(console.getConsoleId());
        assertEquals(consoleOptional.get(), console);
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
        Console savedConsole = consoleRepo.save(console);
        Optional<Console> optionalConsole = consoleRepo.findById(savedConsole.getConsoleId());
        assertEquals(optionalConsole.get(), console);
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
        Console savedConsole = consoleRepo.save(console);

        savedConsole.setModel("2016");
        savedConsole.setManufacturer("BlindsInc");
        savedConsole.setProcessor("D5");
        savedConsole.setMemoryAmount("22GB");
        savedConsole.setPrice(177.00);
        savedConsole.setQuantity(35);
        savedConsole = consoleRepo.save(console);
        Optional<Console> optionalConsole = consoleRepo.findById(console.getConsoleId());
        assertEquals(optionalConsole.get(), console);
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
        Console savedConsole = consoleRepo.save(console);

        consoleRepo.deleteById(savedConsole.getConsoleId());

        Optional<Console> deletedConsole = consoleRepo.findById(console.getConsoleId());
        assertFalse(deletedConsole.isPresent());

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

        List<Console> consoles = consoleRepo.findAll();
        assertEquals(3, consoles.size());
    }

    @Test
    public void testGetConsoleByManufacturer() throws Exception {
        Console console1 = new Console();
        console1.setModel("2015");
        console1.setManufacturer("MirrorsInc");
        console1.setProcessor("E4");
        console1.setMemoryAmount("15GB");
        console1.setPrice(135.00);
        console1.setQuantity(77);

        console1 = consoleRepo.save(console1);

        Console console2 = new Console();
        console2.setModel("2016");
        console2.setManufacturer("TiresInc");
        console2.setProcessor("D5");
        console2.setMemoryAmount("22GB");
        console2.setPrice(177.00);
        console2.setQuantity(35);

        console2 = consoleRepo.save(console2);

        Console console3 = new Console();
        console3.setModel("2017");
        console3.setManufacturer("TiresInc");
        console3.setProcessor("G6");
        console3.setMemoryAmount("44GB");
        console3.setPrice(900.00);
        console3.setQuantity(2005);

        console3 = consoleRepo.save(console3);

        assertEquals(3, consoleRepo.findAll().size());
        assertEquals(2, consoleRepo.findByManufacturer("TiresInc").size());
    }

}