package com.game;

// constants for speed
public enum Speed {

    // the loop checks the modulo of the speed
    // to determine which turns the robots should move
    // fast moves every turn
    // slow moves every other turn
    FAST(1),
    SLOW(2);

    final int speed;

    private Speed(int speed) {
        this.speed = speed;
    }



}
