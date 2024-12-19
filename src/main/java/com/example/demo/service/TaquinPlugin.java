package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Value;



public class TaquinPlugin implements GamePlugin {

    @Value("$(taquin.board.size)")
    int boardSize;

    @Value("$(player.number.taquin)")
    int playerCount;

    String gameType;

    public TaquinPlugin() {
        this.gameType = "15 puzzle";
    }



    @Override
    public Game createGame() {
        GameFactory gameFactory = new TaquinGameFactory();
        return gameFactory.createGame(playerCount, boardSize);
    }

    public String getGameType() {
        return gameType;
    }

}
