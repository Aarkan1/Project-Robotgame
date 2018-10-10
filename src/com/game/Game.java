package com.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// class that contains the game loop
public class Game {

    // default constructor
    public Game() {
    }

    // game loop that updates the output to screen
    public void gameloop() {

        Scanner Scan = new Scanner(System.in);
        int numZe = 0, numGe = 0;
        String str = "";
        boolean inputNum = false;
        Random rnd = new Random();
        int rndX, rndY;

        do {
            System.out.println("Input number of cheetahs: ");
            str = Scan.nextLine();
            if (str.matches("[1-9][0-9]*")) {
                numGe = Integer.valueOf(str);
                inputNum = true;
            } else {
                System.out.println("Only numbers!");
            }

        } while (inputNum == false);

        inputNum = false;

        do {
            System.out.println("Input number of zebras (must be more or equal than cheetahs) ");
            str = Scan.nextLine();
            if (str.matches("[1-9][0-9]*")) {
                numZe = Integer.valueOf(str);
                inputNum = true;
            } else {
                System.out.println("Only numbers!");
            }

        } while ((inputNum == false) || (numZe < numGe));

        // starts off with painting the grid
        Gameboard game = new Gameboard();
        game.gameboard();

        // counter for enabling turnbased movement
        int loopClock = 1;

        // list containing all robots
        ArrayList<Robot> robots = new ArrayList<>();


        // fills list with robot objects
        // and place them randomly on the grid
        for (int i = 0; i < numGe; i++) {
            rndX = rnd.nextInt(Gameboard.GRID_SIZE - 1);
            rndY = rnd.nextInt(Gameboard.GRID_SIZE - 1);
            robots.add(new CheetahRobot(rndX, rndY));
        }
        for (int i = 0; i < numZe; i++) {
            rndX = rnd.nextInt(Gameboard.GRID_SIZE - 1);
            rndY = rnd.nextInt(Gameboard.GRID_SIZE - 1);
            robots.add(new ZebraRobot(rndX, rndY));
        }

        // the game loop
        // ends the game when no zebrarobot remains
        // whenever a cheetah make contact with a zebra, it consumes it
        while (robots.size() - numGe != 0) {

            // increments the counter
            loopClock++;

            // sends the lists with the counter for movement
            game.moveRobot(robots, loopClock);

            // prints out the game
            game.printBoard();

            // controls the speed of the game
            // else it'll be over in an instant
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {

            }
        }
    }
}
