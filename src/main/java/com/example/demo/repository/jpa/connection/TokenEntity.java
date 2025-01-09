package com.example.demo.repository.jpa.connection;


import jakarta.persistence.*;

import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name="Tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "x")
    private int x;

    @Column(name = "y")
    private int y;

    @Column(name = "is_played")
    private boolean isPlayed;

    @Column(name = "ownerId")
    private UUID ownerId;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private GameEntity game;

//    @ManyToOne
//    @JoinColumn(name = "player_id", referencedColumnName = "id")
//    private PlayerEntity player;


    public TokenEntity() {

    }
    public TokenEntity(String name, boolean isPlayed, GameEntity game) {
        this.name = name;
        this.isPlayed = isPlayed;
        this.game = game;
    }

    public String getName() {
        return name;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean getIsPlayed() {
        return isPlayed;
    }

//    public PlayerEntity getPlayer() {
//        return player;
//    }

    public void setPositionX(int x) {
        this.x = x;
    }
    public void setPositionY(int y) {
        this.y = y;
    }

    public void setOwner(Optional<UUID> ownerId) {
        this.ownerId = ownerId.orElse(null);
    }
    //On lui donne un optional en paramètre mais on veut récupérer un UUID à la fin
    //Si l'optionnal contient un UUID il le set
    //sinon (orElse) il met null

}
