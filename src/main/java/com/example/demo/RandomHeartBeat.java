package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class RandomHeartBeat implements HeartBeatSensor{

    @Override
    public int get() {
        return 0;
    }
}
