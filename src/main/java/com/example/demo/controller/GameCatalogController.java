package com.example.demo.controller;

import com.example.demo.service.GameCatalog;
import com.example.demo.service.GameCatalogImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class GameCatalogController {
    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/gamesID")
    public Collection<String> getGamesID() {
        return gameCatalog.getGameIdentifiers();
    }
}
