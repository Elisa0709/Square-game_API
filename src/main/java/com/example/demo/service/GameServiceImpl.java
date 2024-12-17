package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
import java.util.Collection;
import java.util.stream.Stream;


@Service
public class GameServiceImpl implements GameService {
    HashMap<UUID, Game> gameMap = new HashMap<>();



    public Game instanceGame(String gameType, int playerCount, int boardSize){
        GameFactory gameFactory;
        Game game;

        switch(gameType){
            case "tictactoe":
                gameFactory = new TicTacToeGameFactory();
                break;
            case "connect4" :
                gameFactory = new ConnectFourGameFactory();
                break;
            case "15 puzzle" :
                gameFactory = new TaquinGameFactory();
                break;
            default:
                gameFactory = new TicTacToeGameFactory();

        }

        game = gameFactory.createGame(playerCount, boardSize);
        gameMap.put(game.getId(), game); //on stock le jeu dans un tableau associatif dont la clé correspond à son ID
        return game;
    }

    public Game getGame (UUID id){
        return gameMap.get(id);
    }
    public HashMap<UUID, Game> getSavedGames(){
        return gameMap;
    }
    public void deleteGame (UUID id){
        gameMap.remove(id);
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
