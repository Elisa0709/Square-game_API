package com.example.demo.repository.jpa.connection;


import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;



}
