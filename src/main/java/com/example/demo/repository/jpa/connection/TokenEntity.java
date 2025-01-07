package com.example.demo.repository.jpa.connection;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="Tokens")
public class TokenEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "x")
    private int x;

    @Column(name = "y")
    private int y;

    @Column(name = "is_played")
    private boolean isPlayed;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayerEntity player;


    public TokenEntity() {

    }
    public TokenEntity(String name, int x, int y, boolean isPlayed) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.isPlayed = isPlayed;
    }
    public UUID getId() {
        return id;
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
    public PlayerEntity getPlayer() {
        return player;
    }

}
