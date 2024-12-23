package com.example.demo.repository;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class InMemoryGameDao implements GameDao {
    HashMap<UUID, Game> currentGamesList = new HashMap<>();

    @Override
    public Game getGameById(UUID id) {
        return currentGamesList.get(id);
    }

    @Override
    public HashMap<UUID, Game> getCurrentGamesList() {
        return currentGamesList;
    }

    @Override
    public void deleteGame(UUID id) {
        currentGamesList.remove(id);
    }

    @Override
    public void addCurrentGame(Game game) {
        currentGamesList.put(game.getId(), game);
    }


}
