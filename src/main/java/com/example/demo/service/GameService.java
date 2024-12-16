package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;

public interface GameService {

    public Game instanceGame(String gameType, int playerCount, int boardSize);
}
