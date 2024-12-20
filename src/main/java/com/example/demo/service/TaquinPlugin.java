package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TaquinPlugin implements GamePlugin {

    @Value("${taquin.board.size}")
    int boardSize;

    @Value("${player.number.taquin}")
    int playerCount;

    @Autowired
    private MessageSource messageSource;

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

    @Override
    public String getName(Locale language) {
        return messageSource.getMessage("game.15puzzle.name", null, language);
    }

}
