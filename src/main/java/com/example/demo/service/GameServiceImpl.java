package com.example.demo.service;

import com.example.demo.repository.GameDao;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;


@Service
public class GameServiceImpl {
    //HashMap<UUID, Game> gameMap = new HashMap<>();

    @Autowired
    List<GamePlugin> gamePluginList;

    @Autowired
    GameDao gameDao;


    public Game instanceGame(String gameType) {
        Game game = gamePluginList.stream()
                .filter(item -> item.getGameType().equals(gameType))
                .findFirst()
                .map(gamePlugin -> gamePlugin.createGame())
                .orElse(null); //si map est vide


        gameDao.addCurrentGame(game);
        return game;
    }


    public Game getGame(UUID id) {
        return gameDao.getGameById(id);
    }

    public HashMap<UUID, Game> getSavedGames() {
        return gameDao.getCurrentGamesList();
    }

    public void deleteGame(UUID id) {
        gameDao.deleteGame(id);
    }

    private static Token getTokenWithName(Game game, String tokenName) {
        return Stream.of(game.getRemainingTokens(), game.getRemovedTokens(), game.getBoard().values())
                .flatMap(Collection::stream)
                .filter(t -> t.getName().equals(tokenName))
                .filter(t -> t.canMove())
                .findFirst()
                .orElse(null);
    }

    public void playTurn(UUID gameId, String tokenName, int x, int y) throws InvalidPositionException {
        Game game = getGame(gameId);
        Token token = getTokenWithName(game, tokenName);
        token.moveTo(new CellPosition(x, y));
    }

}
