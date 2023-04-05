package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    ConsoleRepository consoleRepo;

    @Autowired
    GameRepository gameRepo;

    @QueryMapping
    public List<Console> findConsoles() {
        return consoleRepo.findAll();
    }

    @QueryMapping
    public Optional<Console> findConsoleById(@Argument Integer console_id)  {
        return consoleRepo.findById(console_id);
    }
    @QueryMapping
    public List<Console> findConsolesByManufacturer(@Argument String manufacturer_id) {
        return consoleRepo.findByManufacturer(manufacturer_id);
    }

    @QueryMapping
    public List<Game> findGames() {
        return gameRepo.findAll();
    }
    @QueryMapping
    public Optional<Game> findGameById(@Argument Integer game_id)  {
        return gameRepo.findById(game_id);
    }
    @QueryMapping
    public List<Game> findGameByTitle(@Argument String title) {
        return gameRepo.findByTitle(title);
    }
    @QueryMapping
    public List<Game> findGameByesrbRating(@Argument String esrb_rating) {
        return gameRepo.findByesrbRating(esrb_rating);
    }
    @QueryMapping
    public List<Game> findGameByStudio(@Argument String studio) {
        return gameRepo.findByStudio(studio);
    }

}
