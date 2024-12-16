package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class GameCatalogImpl implements GameCatalog {
    TicTacToeGameFactory ticTacToeGameFactory;
    TaquinGameFactory taquinGameFactory;
    ConnectFourGameFactory connectFourGameFactory;

    public GameCatalogImpl() {
        this.ticTacToeGameFactory = new TicTacToeGameFactory();
        this.taquinGameFactory = new TaquinGameFactory();
        this.connectFourGameFactory = new ConnectFourGameFactory();
    }


    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getGameFactoryId(), taquinGameFactory.getGameFactoryId(), connectFourGameFactory.getGameFactoryId());
    }
}
