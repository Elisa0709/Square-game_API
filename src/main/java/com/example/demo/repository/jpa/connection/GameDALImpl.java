package com.example.demo.repository.jpa.connection;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

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

    public void saveTokensInDb(Game game, GameEntity gameEntity) {

        //stocker les remainingtokens (game.getRemainingTokens)
        Collection<Token> remainingTokens = game.getRemainingTokens();

        for (Token token : remainingTokens) {  //remaining = restant, disponible
            TokenEntity tokenEntity = new TokenEntity(
                    token.getName(),
                    false,
                    gameEntity
            );

            tokenEntity.setOwner(token.getOwnerId());
            tokenRepository.save(tokenEntity);
        }

        //stocker les removed tokens
        Collection<Token> removedTokens = game.getBoard().values();
        for (Token token : removedTokens) {
            TokenEntity tokenEntity = new TokenEntity(
                    token.getName(),
                    true,
                    gameEntity
            );
            tokenEntity.setOwner(token.getOwnerId());
            tokenEntity.setPositionX(token.getPosition().x());
            tokenEntity.setPositionY(token.getPosition().y());
            tokenRepository.save(tokenEntity);
        }
    }

    public void deleteGameByIdInDb(UUID id){
        gameRepository.deleteById(id);
    };

    public void deleteAllGame(){
        gameRepository.deleteAll();
    }

    public Iterable<GameEntity> getAllGames(){
       return gameRepository.findAll();
    }

    public GameEntity getGameById(UUID id){
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<TokenEntity> getTokensByName(UUID gameId, String name) {
        return tokenRepository.findTokensByNameAndGameId(name, gameId);
    }

    @Override
    public TokenEntity findOneToken(String name, UUID gameId) {
       return tokenRepository.findOneToken(name, gameId);
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
