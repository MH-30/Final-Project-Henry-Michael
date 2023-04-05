package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    private GameRepository gameRepo;

    //Create
    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {
        return gameRepo.save(game);
    }
    // Read by Id
    @GetMapping("/game/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById (@PathVariable Integer id){
        Optional<Game> returnGame = gameRepo.findById(id);
        if (returnGame.isPresent()) return returnGame.get();
        return null;
    }
    // Update by id
    @PutMapping("/game/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGameById (@RequestBody Game game){
        gameRepo.save(game);
    }

    @DeleteMapping("/game/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame (@PathVariable Integer id){
     gameRepo.deleteById(id);
    }

    @GetMapping("/game/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGame (){
               return gameRepo.findAll();
    }
    @GetMapping("/game/studio/{studio}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGameByStudio(@PathVariable String studio) {
        return gameRepo.findByStudio(studio);
    }
    @GetMapping("/game/ESRB/{esrbRating}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGameByESRB(@PathVariable String esrbRating) {
        return gameRepo.findByesrbRating(esrbRating);
    }
    @GetMapping("/game/title/{title}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getGameByTitle(@PathVariable String title) {
        return gameRepo.findByTitle(title);
    }

}
