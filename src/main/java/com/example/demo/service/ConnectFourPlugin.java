package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;

import java.util.Locale;

public class ConnectFourPlugin implements GamePlugin {
    @Value("$(taquin.board.size)")
    int boardSize;

    @Value("$(player.number.taquin)")
    int playerCount;

    String gameType;

    public ConnectFourPlugin() {
        this.gameType = "connect4";
    }

    @Override
    public Game createGame() {
        GameFactory gameFactory = new ConnectFourGameFactory();
        return gameFactory.createGame(playerCount, boardSize);
    }

    public String getGameType() {
        return gameType;
    }
    @Override
    public String getName(Locale language) {
        return "";
    }
}
