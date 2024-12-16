package com.example.demo.controller;

import com.example.demo.service.HeartBeatSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {
    @Autowired
    private HeartBeatSensor heartBeatSensor;

    @GetMapping("/heartbeat")
    public int getHeartBeat() {
       return heartBeatSensor.get();
    }
}
