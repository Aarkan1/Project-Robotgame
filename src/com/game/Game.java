package com.game;

import java.util.ArrayList;

// class that contains the game loop
public class Game {

    public void gameloop() {

        // starts off with painting the grid
        Gameboard game = new Gameboard();
        game.gameboard();

        // counter for enabling turnbased movement
        int loopClock = 1;

        // creates two lists for holding the different robot objects
        ArrayList<Robot> zebras = new ArrayList<>();
        ArrayList<Robot> cheetahs = new ArrayList<>();

        // fills lists with robot objects
        for (int i = 0; i < 10; i++) {
            zebras.add(new ZebraRobot());
            cheetahs.add(new CheetahRobot());
        }

        // the game loop
        // ends the game when no zebrarobot remains
        // whenever a cheetah make contact with a zebra, it consumes it
        while (!zebras.isEmpty()) {

            // increments the counter
            loopClock++;

            // sends the lists with the counter for movement
            game.moveRobot(zebras, cheetahs, loopClock);

            // prints out the game
            game.printBoard();

            // controls the speed of the game
            // else it'll be over in an instant
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {

            }
        }
    }
}