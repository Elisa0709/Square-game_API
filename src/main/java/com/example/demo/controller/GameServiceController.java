package com.example.demo.controller;

import com.example.demo.controller.dto.GameCreationParams;
import com.example.demo.controller.dto.PlayTurnParams;
import com.example.demo.repository.jpa.connection.GameEntity;
import com.example.demo.service.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GameServiceController {
    @Autowired
    GameServiceImpl gameServiceImpl;

    Game game;

    @PostMapping("/games")
    public Game createGame(@RequestBody GameCreationParams params){
        game = gameServiceImpl.instanceGame(params.gameType);
        return game;
    }

    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable UUID gameId){
       return gameServiceImpl.getGameById(gameId);
    }

    @GetMapping("/games")
    public Iterable<GameEntity> getSavedGames(){
        return gameServiceImpl.getSavedGames();
    }

    @DeleteMapping("/games/{gameId}")
    public void deleteGame(@PathVariable UUID gameId){
        gameServiceImpl.deleteGameById(gameId);
    }

    @DeleteMapping("/games")
    public void deleteGame(){
        gameServiceImpl.deleteAllGames();
    }

    @GetMapping("/games/{gameId}/tokens/{name}")
    public Object getTokenByName(@PathVariable UUID gameId, @PathVariable String name){
        return gameServiceImpl.getTokensByName(gameId, name);
    }

    @PutMapping("/games/{gameId}/tokens/{tokenName}/position")
    public void placeToken(@PathVariable UUID gameId, @PathVariable String tokenName, @RequestBody PlayTurnParams params) throws InvalidPositionException {
        gameServiceImpl.playTurn(gameId, tokenName, params.x, params.y);
    }

}
