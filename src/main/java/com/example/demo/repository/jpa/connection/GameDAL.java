package com.example.demo.repository.jpa.connection;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;
import java.util.UUID;

public interface GameDAL {

    void saveGameDataInDb(Game game);

    void saveTokensInDb(Game game, GameEntity gameEntity);

    void deleteGameByIdInDb(UUID id);

    Iterable<GameEntity> getAllGames();

    GameEntity getGameById(UUID id);

    Collection<TokenEntity> getTokensByName(UUID gameId, String name);

    TokenEntity findOneToken(String name, UUID gameId);
}
