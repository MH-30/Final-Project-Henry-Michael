package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {

    @Autowired
    private ConsoleRepository consoleRepo;

    //Create
    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createconsole(@RequestBody Console console) {
        return consoleRepo.save(console);
    }
    //Get by id
    @GetMapping("/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById (@PathVariable Integer id){
        Optional<Console> returnconsole = consoleRepo.findById(id);
        if (returnconsole.isPresent()) return returnconsole.get();
        return null;
    }
    // Update by id
    @PutMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsoleById (@RequestBody Console console){
        consoleRepo.save(console);
    }
    //Delete by id
    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole (@PathVariable Integer id) {
        consoleRepo.deleteById(id);
    }
    @GetMapping("/console/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole (){
        return consoleRepo.findAll();
    }
    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return consoleRepo.findByManufacturer(manufacturer);
    }

}
