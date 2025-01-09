package com.example.demo.repository.jpa.connection;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class GameDALImpl implements GameDAL {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PlayerRepository playerRepository;


    public void saveGameDataInDb(Game game) {
        GameEntity gameEntity = new GameEntity(
                game.getBoardSize(),
                game.getStatus().name(),
                game.getFactoryId()
        );
        gameRepository.save(gameEntity);

//        savePlayersInDb(game, gameEntity);
        saveTokensInDb(game, gameEntity);
    }


    private void saveTokensInDb(Game game, GameEntity gameEntity) {

        //stocker les remainingtokens (game.getRemainingTokens)

        Collection<Token> remainingTokens = game.getRemainingTokens();

        for (Token token : remainingTokens) {  //remaining = restant, disponible
            TokenEntity tokenEntity = new TokenEntity(
                    token.getName(),
                    false
            );

            tokenEntity.setOwner(token.getOwnerId());
            tokenRepository.save(tokenEntity);
        }

        //stocker les removed tokens
        Collection<Token> removedTokens = game.getRemovedTokens();
        for (Token token : removedTokens) {
            TokenEntity tokenEntity = new TokenEntity(
                    token.getName(),
                    true
            );
            tokenEntity.setOwner(token.getOwnerId());
            tokenEntity.setPositionX(token.getPosition().x());
            tokenEntity.setPositionY(token.getPosition().y());
            tokenRepository.save(tokenEntity);
        }
    }


    //private void savePlayersInDb(Game game, GameEntity gameEntity) {
//        Set<UUID> playersIds = game.getPlayerIds();
//
//        for (UUID playerId : playersIds) {
//            PlayerEntity playerEntity = new PlayerEntity(playerId, gameEntity);
//
//            playerRepository.save(playerEntity);
//        }
//    }

}
