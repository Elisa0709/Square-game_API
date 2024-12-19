package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.cglib.core.Local;

import java.util.Locale;

public interface GamePlugin {

      public Game createGame();
      public String getGameType();
      public String getName(Locale language);

}
