package com.example.demo.repository.jpa.connection;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name="players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "player_id")
    private UUID playerId;

    @Column(name = "player_type")
    private String playerType;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @OneToMany(mappedBy = "player")
    private List<GameEntity> games;


}
