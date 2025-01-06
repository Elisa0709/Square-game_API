package com.example.demo.repository.jpa.connection;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name="games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "game_id")
    private UUID gameId;

    @Column(name = "board_size")
    private int boardSize;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "game")
    private List<PlayerEntity> players;




}
