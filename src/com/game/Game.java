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

        // starts off with painting the grid
        Gameboard game = new Gameboard();
        game.gameBoard();

        // variables for input and generating robots
        Scanner Scan = new Scanner(System.in);
        int numZe = 0, numGe = 0;
        String str = "";
        boolean inputNum = false;
        Random rnd = new Random();
        int rndX, rndY;

        /*

        // controlled inputs with loops
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
            System.out.println("Input number of zebras (must be more or equal to cheetahs) ");
            str = Scan.nextLine();
            if (str.matches("[1-9][0-9]*")) {
                numZe = Integer.valueOf(str);
                inputNum = true;
            } else {
                System.out.println("Only numbers!");
            }

        } while ((inputNum == false) || (numZe < numGe));


*/

        // counter for enabling turnbased movement
        int loopClock = 1;

        // list containing all robots
        ArrayList<Robot> robots = new ArrayList<>();


        // fills list with robot objects
        // and place them randomly on the grid
        for (int i = 0; i < 4; i++) {
            rndX = rnd.nextInt(Gameboard.GRID_SIZE - 1);
            rndY = rnd.nextInt(Gameboard.GRID_SIZE - 1);
            robots.add(new CheetahRobot(rndY, rndX));
        }
        for (int i = 0; i < 15; i++) {
            rndX = rnd.nextInt(Gameboard.GRID_SIZE - 1);
            rndY = rnd.nextInt(Gameboard.GRID_SIZE - 1);
            robots.add(new ZebraRobot(rndY, rndX));
        }

        // the game loop
        // ends the game when no zebrarobot remains
        // whenever a cheetah make contact with a zebra, it consumes it
        while (robots.size() - 4 != 0) {

            // increments the counter
            loopClock++;

            // sends the lists with the counter for movement
            game.moveRobot(robots, loopClock);

            // prints out the game
//            game.printBoard();

            // controls the speed of the game
            // else it'll be over in an instant
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {

            }
        }
    }
}
