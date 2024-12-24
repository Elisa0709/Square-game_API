package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
    // Instance unique du singleton
    private static DatabaseSingleton instance;

    // Objet de connexion
    private Connection connection;

    // Informations de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/square_games";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Constructeur privé pour empêcher l'instanciation externe
    private DatabaseSingleton() {
        try {
            // Charger le driver JDBC de MySQL
            //Class.forName("com.mysql.cj.jdbc.Driver");
            // Créer une connexion
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Méthode pour obtenir l'instance unique
    public static DatabaseSingleton getInstance()  {
        if (instance == null) {
            synchronized (DatabaseSingleton.class) {
                if (instance == null) {
                    instance = new DatabaseSingleton();
                }
            }
        }
        return instance;
    }

    // Méthode pour obtenir la connexion
    public Connection getConnection() {
        return connection;
    }
}
