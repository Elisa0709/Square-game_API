package com.example.demo.controller;

import com.example.demo.controller.dto.GameCreationParams;
import com.example.demo.controller.dto.PlayTurnParams;
import com.example.demo.service.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
public class GameServiceController {
    @Autowired
    GameServiceImpl gameServiceImpl;

    Game game;

    @PostMapping("/games")
    public Game createGame(@RequestBody GameCreationParams params){
        game = gameServiceImpl.instanceGame(params.gameType, params.playerCount, params.boardSize);
        return game;
    }

    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable UUID gameId){
       return gameServiceImpl.getGame(gameId);

    }

    @GetMapping("/games")
    public HashMap<UUID, Game> getSavedGames(){
        return gameServiceImpl.getSavedGames();
    }

    @DeleteMapping("/games/{gameId}")
    public void deleteGame(@PathVariable UUID gameId){
        gameServiceImpl.deleteGame(gameId);
    }

    @PutMapping("/games/{gameId}/tokens/{tokenName}/position")
    public void placeToken(@PathVariable UUID gameId, @PathVariable String tokenName, @RequestBody PlayTurnParams params) throws InvalidPositionException {
        gameServiceImpl.playTurn(gameId, tokenName, params.x, params.y);
    }


}
