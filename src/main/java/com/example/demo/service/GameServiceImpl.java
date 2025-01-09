package com.example.demo.service;

import com.example.demo.repository.jdbc.connection.GameDao;
import com.example.demo.repository.jpa.connection.*;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;


@Service
public class GameServiceImpl {

    @Autowired
    List<GamePlugin> gamePluginList;

    @Autowired
    GameDao gameDao; //à supprimer plus tard

    @Autowired
    GameDALImpl gameDAL;    //DAL = data access layer





    public Game instanceGame(String gameType) {
        Game game = gamePluginList.stream()
                .filter(item -> item.getGameType().equals(gameType))
                .findFirst()
                .map(gamePlugin -> gamePlugin.createGame())
                .orElse(null); //si map est vide

        gameDAL.saveGameDataInDb(game);

        return game;
    }


    public GameEntity getGameById(UUID id) {

        return gameDAL.getGameById(id);
    }

    public Iterable<GameEntity> getSavedGames() {
        return gameDAL.getAllGames();
    }

    public void deleteGameById(UUID id) {
        gameDAL.deleteGameByIdInDb(id);
    }

    public void deleteAllGames() {
        gameDAL.deleteAllGame();
    }

    public Collection<TokenEntity> getTokensByName(UUID gameId, String name) {
        return gameDAL.getTokensByName(gameId, name);
    }

    private static Token getTokenWithName(Game game, String tokenName) {
        return Stream.of(game.getRemainingTokens(), game.getRemovedTokens(), game.getBoard().values())
                .flatMap(Collection::stream)
                .filter(t -> t.getName().equals(tokenName))
                .filter(t -> t.canMove())
                .findFirst()
                .orElse(null);
    }

    public void playTurn(GameEntity gameEntity, String tokenName, int x, int y) throws InvalidPositionException {

        //instancier un jeu (game engine) à partir des données du jeu dans la bdd


        Game game =

        Game game = getGameById(gameId);
        Token token = getTokenWithName(game, tokenName);
        token.moveTo(new CellPosition(x, y));
    }


}
