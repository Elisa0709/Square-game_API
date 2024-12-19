package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;

public interface GamePlugin {

      public Game createGame();
      public String getGameType();


}
