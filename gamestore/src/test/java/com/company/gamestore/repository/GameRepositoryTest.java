package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Shirt;
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
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepo;

    @Before
    public void setUp() throws Exception {
        gameRepo.deleteAll();
    }

    @Test
    public void testCreateGame() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(42.00);
        game.setQuantity(10);
        game.setStudio("Epic");
        // Convert author object into Json
        game = gameRepo.save(game);
        Optional<Game> gameOptional = gameRepo.findById(game.getGameId());
        assertEquals(gameOptional.get(), game);
    }

    @Test
    public void testGetGameById() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(42.00);
        game.setQuantity(10);
        game.setStudio("Epic");
        game = gameRepo.save(game);
        Optional<Game> gameOptional = gameRepo.findById(game.getGameId());
        assertEquals(gameOptional.get(), game);
    }

    @Test
    public void testUpdateGameById() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(42.00);
        game.setQuantity(10);
        game.setStudio("Epic");
        game = gameRepo.save(game);

        game.setEsrbRating("fine");
        game.setDescription("Fine game");
        game.setPrice(50.00);
        game.setQuantity(12);
        game.setStudio("Icon");
        game = gameRepo.save(game);
        Optional<Game> gameOptional = gameRepo.findById(game.getGameId());
        assertEquals(gameOptional.get(), game);
    }

    @Test
    public void testDeleteGame() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(42.00);
        game.setQuantity(10);
        game.setStudio("Epic");
        game = gameRepo.save(game);

        gameRepo.deleteById(game.getGameId());

        Optional<Game> deletedGame = gameRepo.findById(game.getGameId());
        assertFalse(deletedGame.isPresent());
    }

    @Test
    public void testGetAllGames() throws Exception {
        Game game1 = new Game();
        game1.setTitle("AroundTown");
        game1.setEsrbRating("solid");
        game1.setDescription("Nice game");
        game1.setPrice(42.00);
        game1.setQuantity(10);
        game1.setStudio("Epic");

        Game game2 = new Game();
        game2.setTitle("AroundTown");
        game2.setEsrbRating("fine");
        game2.setDescription("Fine game");
        game2.setPrice(50.00);
        game2.setQuantity(12);
        game2.setStudio("Icon");

        Game game3 = new Game();
        game3.setTitle("AroundTown");
        game3.setEsrbRating("awesome");
        game3.setDescription("awesome game");
        game3.setPrice(37.00);
        game3.setQuantity(15);
        game3.setStudio("Dynamic");

        gameRepo.save(game1);
        gameRepo.save(game2);
        gameRepo.save(game3);
        List<Game> games = gameRepo.findAll();
        assertEquals(3, games.size());
    }

    @Test
    public void testGetGameByStudio() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(42.00);
        game.setQuantity(10);
        game.setStudio("Epic");
        game = gameRepo.save(game);

        assertEquals(1, gameRepo.findAll().size());

        Game game2 = new Game();
        game2.setTitle("AroundTown");
        game2.setEsrbRating("fine");
        game2.setDescription("Fine game");
        game2.setPrice(50.00);
        game2.setQuantity(12);
        game2.setStudio("Icon");

        game2 = gameRepo.save(game2);

        Game game3 = new Game();
        game3.setTitle("AroundTown");
        game3.setEsrbRating("awesome");
        game3.setDescription("awesome game");
        game3.setPrice(37.00);
        game3.setQuantity(15);
        game3.setStudio("Epic");

        game3 = gameRepo.save(game3);

        assertEquals(3, gameRepo.findAll().size());
        assertEquals(2, gameRepo.findByStudio("Epic").size());
    }

    @Test
    public void testGetGameByESRB() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(42.00);
        game.setQuantity(10);
        game.setStudio("Epic");
        game = gameRepo.save(game);

        assertEquals(1, gameRepo.findAll().size());

        Game game2 = new Game();
        game2.setTitle("AroundTown");
        game2.setEsrbRating("awesome");
        game2.setDescription("Fine game");
        game2.setPrice(50.00);
        game2.setQuantity(12);
        game2.setStudio("Icon");

        game2 = gameRepo.save(game2);

        Game game3 = new Game();
        game3.setTitle("AroundTown");
        game3.setEsrbRating("awesome");
        game3.setDescription("awesome game");
        game3.setPrice(37.00);
        game3.setQuantity(15);
        game3.setStudio("Epic");

        game3 = gameRepo.save(game3);

        assertEquals(3, gameRepo.findAll().size());
        assertEquals(2, gameRepo.findByesrbRating("awesome").size());
    }

    @Test
    public void testGetGameByTitle() throws Exception {
        Game game = new Game();
        game.setTitle("AroundTown");
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(42.00);
        game.setQuantity(10);
        game.setStudio("Epic");
        game = gameRepo.save(game);

        assertEquals(1, gameRepo.findAll().size());

        Game game2 = new Game();
        game2.setTitle("AroundTown");
        game2.setEsrbRating("fine");
        game2.setDescription("Fine game");
        game2.setPrice(50.00);
        game2.setQuantity(12);
        game2.setStudio("Icon");

        game2 = gameRepo.save(game2);

        Game game3 = new Game();
        game3.setTitle("CityLights");
        game3.setEsrbRating("awesome");
        game3.setDescription("awesome game");
        game3.setPrice(37.00);
        game3.setQuantity(15);
        game3.setStudio("Epic");

        game3 = gameRepo.save(game3);

        assertEquals(3, gameRepo.findAll().size());
        assertEquals(2, gameRepo.findByTitle("AroundTown").size());
    }
}