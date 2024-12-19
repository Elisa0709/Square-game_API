package com.example.demo.service;

import com.example.demo.heartbeat.HeartBeatSensor;
import org.springframework.stereotype.Service;

@Service
public class RandomHeartBeat implements HeartBeatSensor {

    @Override
    public int get() {
        return 0;
    }
}
