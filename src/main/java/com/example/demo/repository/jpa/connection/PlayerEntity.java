package com.example.demo.repository.jpa.connection;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "player_type")
    private String playerType;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @OneToMany
    @JoinColumn(name = "token_id")
    private List<TokenEntity> tokens;


    public PlayerEntity() {

    }
    public PlayerEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }



    public String getPlayerType() {
        return playerType;
    }

    public GameEntity getGameId() {
        return game;
    }


}
