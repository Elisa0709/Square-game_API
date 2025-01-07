package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ConnectFourPlugin implements GamePlugin {
    @Value("${connect4.board.size}")
    //@Value("#{new Integer('${connect4.board.size}')}")
    int boardSize;

    @Value("${player.number.default}")
    int playerCount;

    String gameType;

    @Autowired
    private MessageSource messageSource;

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
        return messageSource.getMessage("game.connect4.name", null, language);
    }

}
