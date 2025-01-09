package com.example.demo.repository.jdbc.connection;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.HashMap;
import java.util.UUID;

public interface GameDao {
    public Game getGameById(UUID id);
    public HashMap<UUID, Game> getCurrentGamesList();
    public void deleteGame(UUID id);
    public void addCurrentGame(Game game);

}
