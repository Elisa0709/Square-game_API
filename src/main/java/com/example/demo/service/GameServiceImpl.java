package com.example.demo.service;

import com.example.demo.repository.jdbc.connection.GameDao;
import com.example.demo.repository.jpa.connection.GameEntity;
import com.example.demo.repository.jpa.connection.GameRepository;
import com.example.demo.repository.jpa.connection.PlayerEntity;
import com.example.demo.repository.jpa.connection.PlayerRepository;
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
        gameDao.addCurrentGame(game); //ici utiliser save de bdd entité game

        saveGame(game);

        return game;
    }

    private void saveGame(Game game) {
        GameEntity gameEntity = new GameEntity(
                game.getBoardSize(),
                game.getStatus().name(),
                game.getFactoryId()
        );

        gameRepository.save(gameEntity);

        //je veu récupérer chaque player qui a été créé avec le game juste en haut, et enregistrer chaque player 1 par 1
        //dans la bdd via des playerEntity
        //pareil pour les tokens

        //???faire un map de getPlayerIds et pour chacun des player instancier une playerEntity


        for (int i = 0; i < game.getPlayerIds().size(); i++) {
            //pour chaque itération on transforme le resultat n playerEntity
            //et on fait playerRepository.save(playerEntity)
        }



//        playerRepository.save(game.getPlayerIds());





        //enregistrer les 2 utilisateurs de game dans la db
        //créer les token de game dans la db
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
        token.moveTo(new CellPosition( x, y));
    }





}
