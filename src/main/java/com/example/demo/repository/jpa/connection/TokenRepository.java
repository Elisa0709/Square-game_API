package com.example.demo.repository.jpa.connection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, Integer> {

    @Query("SELECT t FROM TokenEntity t WHERE t.name = :name AND t.game.id = :gameId")
    List<TokenEntity> findTokensByNameAndGameId(@Param("name") String name, @Param("gameId") UUID gameId);

    @Query(value = "SELECT * FROM Tokens WHERE name = :name AND game_id = :gameId AND is_played = false LIMIT 1", nativeQuery = true)
    TokenEntity findOneToken(@Param("name") String name, @Param("gameId") UUID gameId);



}
