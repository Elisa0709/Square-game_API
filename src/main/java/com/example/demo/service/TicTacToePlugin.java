package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TicTacToePlugin implements GamePlugin {

    @Value("${ticTacToe.board.size}")
    int boardSize;

    @Value("${player.number.default}" )
    int playerCount;

    String gameType;

    public TicTacToePlugin() {
        this.gameType = "tictactoe";
    }

    @Override
    public Game createGame() {
        GameFactory gameFactory = new TicTacToeGameFactory();
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
