package com.example.demo.controller;

import com.example.demo.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = GameServiceController.class)
public class GameServiceControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameService gameService;

    @Test
    void shouldCreateGame() throws Exception {

    }




}
