package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameRepository gameRepo;

    @Test
    public void testCreateGame() throws Exception {
        Game game = new Game();
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");

        String inputJson = objectMapper.writeValueAsString(game);
        mockMvc.perform(MockMvcRequestBuilders.post("/game").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetGameById() throws Exception {
        Game game = new Game();
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");
        game.setGameId(1);
        gameRepo.save(game);
        String inputJson = objectMapper.writeValueAsString(game);
        mockMvc.perform(MockMvcRequestBuilders.get("/game/1")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void testUpdateGameById() throws Exception {
        Game game = new Game();
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");
        game.setGameId(1);
        gameRepo.save(game);

        game.setEsrbRating("not solid");
        game.setDescription("not Nice game");
        game.setPrice(BigDecimal.valueOf(40.00));
        game.setQuantity(11);
        game.setStudio("Not Epic");

        gameRepo.save(game);
        String inputJson = objectMapper.writeValueAsString(game);

        mockMvc.perform(put("/game/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void testDeleteGame() throws Exception {
        Game game = new Game();
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");
        game.setGameId(1);
        gameRepo.save(game);
        gameRepo.deleteById(game.getGameId());
        mockMvc.perform(delete("/game/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllGames() throws Exception {
        Game game1 = new Game();
        game1.setEsrbRating("solid");
        game1.setDescription("Nice game");
        game1.setPrice(BigDecimal.valueOf(42.00));
        game1.setQuantity(10);
        game1.setStudio("Epic");

        Game game2 = new Game();
        game2.setEsrbRating("fine");
        game2.setDescription("Fine game");
        game2.setPrice(BigDecimal.valueOf(50.00));
        game2.setQuantity(12);
        game2.setStudio("Icon");

        Game game3 = new Game();
        game3.setEsrbRating("awesome");
        game3.setDescription("awesome game");
        game3.setPrice(BigDecimal.valueOf(37.00));
        game3.setQuantity(15);
        game3.setStudio("Dynamic");

        gameRepo.save(game1);
        gameRepo.save(game2);
        gameRepo.save(game3);

        mockMvc.perform(MockMvcRequestBuilders.get("/game/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGameByStudio() throws Exception {
        Game game = new Game();
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");
        gameRepo.save(game);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/game/studio/Epic")
                .contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void testGetGameByESRB() throws Exception {
        Game game = new Game();
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");
        gameRepo.save(game);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/game/ESRB/solid")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetGameByTitle() throws Exception {
        Game game = new Game();
        game.setEsrbRating("solid");
        game.setDescription("Nice game");
        game.setPrice(BigDecimal.valueOf(42.00));
        game.setQuantity(10);
        game.setStudio("Epic");
        game.setTitle("NBA2k");
        gameRepo.save(game);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/game/title/NBA2k")
                .contentType(MediaType.APPLICATION_JSON));

    }
}