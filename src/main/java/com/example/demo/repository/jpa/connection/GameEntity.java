package com.example.demo.repository.jpa.connection;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name="games")
public class GameEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type")
    private String type;

    @Column(name = "board_size")
    private int boardSize;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "game")
    private List<PlayerEntity> players;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL) //grâce à cascade, si on supprime la table game ça supprimera aussi les tokens qui lui sont associé
    private List<TokenEntity> tokens ;

    public GameEntity() {

    }
    public GameEntity(int boardSize, String status, String type) {
        this.boardSize = boardSize;
        this.status = status;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }
    public int getBoardSize() {
        return boardSize;
    }
    public String getStatus() {
        return status;
    }
    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public String getType() {
        return type;
    }
}
