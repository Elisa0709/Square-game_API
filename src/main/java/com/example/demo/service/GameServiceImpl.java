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
    GameDao gameDao;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;


    public Game instanceGame(String gameType) {
        Game game = gamePluginList.stream()
                .filter(item -> item.getGameType().equals(gameType))
                .findFirst()
                .map(gamePlugin -> gamePlugin.createGame())
                .orElse(null); //si map est vide

        gameDao.addCurrentGame(game);

        saveGameDataInDb(game);

        return game;
    }

    private void saveGameDataInDb(Game game) {
        GameEntity gameEntity = new GameEntity(
                game.getBoardSize(),
                game.getStatus().name(),
                game.getFactoryId()
        );
        gameRepository.save(gameEntity);

        savePlayersInDb(game, gameEntity);
        //saveTokensInDb(game);
    }

    private void savePlayersInDb(Game game, GameEntity gameEntity) {
        Set<UUID> playersIds = game.getPlayerIds();

        for (UUID playerId : playersIds) {
            PlayerEntity playerEntity = new PlayerEntity(playerId, gameEntity);

            playerRepository.save(playerEntity);
        }
    }


//
//    private void saveTokensInDb(Game game, GameEntity gameEntity){
//
//        //stocker les remainingtokens (game.getRemainingTokens)
//
////        Collection<Token> remainingTokens = game.getRemainingTokens();
////
////        for (Token token : remainingTokens) {
////            TokenEntity tokenEntity = new TokenEntity(
////                    token.getName(),
////
////            );
//       // }
//
//
//        //stocker les autres tokens
//
//        //accéder aux tokens
//        //trier les tokens remain des autres ?
//        //les mettre dans une entity
//        //les save avec token repository
//    }


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
        token.moveTo(new CellPosition( x, y));
    }





}
