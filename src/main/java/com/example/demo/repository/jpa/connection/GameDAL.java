package com.example.demo.repository.jpa.connection;

import fr.le_campus_numerique.square_games.engine.Game;

public interface GameDAL {
    public void saveGameDataInDb(Game game);
    public void saveTokensInDb(Game game, GameEntity gameEntity);
}
