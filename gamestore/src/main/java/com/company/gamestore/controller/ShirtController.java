package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Shirt;
import com.company.gamestore.repository.ShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class ShirtController {
    @Autowired
    private ShirtRepository shirtRepo;

    @PostMapping("/shirt")
    @ResponseStatus(HttpStatus.CREATED)
    public Shirt createShirt(@RequestBody Shirt shirt) {
        return shirtRepo.save(shirt);
    }

    @DeleteMapping("/shirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShirt(@PathVariable Integer id) {
        shirtRepo.deleteById(id);
    }

    @PutMapping("/shirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateShirt(@RequestBody Shirt shirt) {
        shirtRepo.save(shirt);
    }

    @GetMapping("/shirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Shirt getShirtById(@PathVariable Integer id) {
        Optional<Shirt> returnShirt = shirtRepo.findById(id);
        if (returnShirt.isPresent()) return returnShirt.get();
        return null;
    }

    @GetMapping("/shirt/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Shirt> getAllShirts() {
        return shirtRepo.findAll();
    }

    @GetMapping("/shirt/color/{color}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Shirt> getShirtByColor(@PathVariable String color) {
        return shirtRepo.findByColor(color);
    }

    @GetMapping("/shirt/size/{size}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Shirt> getShirtBySize(@PathVariable String size) {
        return shirtRepo.findBySize(size);
    }
}
