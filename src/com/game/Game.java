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


        // variables for input and generating robots
        Scanner Scan = new Scanner(System.in);
        int numZe = 0, numGe = 0;
        String str = "";
        boolean inputNum = false;
        Random rnd = new Random();
        int rndX, rndY;
        boolean running = true;

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

        // lists containing objects
        ArrayList<Robot> robots = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();

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

        // adds home tree to itemslist
        items.add(new Tree());
        items.add(new Stone(2,3, true));
        items.add(new Stone(3,Gameboard.GRID_SIZE - 4, true));
        items.add(new Stone(Gameboard.GRID_SIZE - 3, 9, true));
        items.add(new Stone(Gameboard.GRID_SIZE - 4, 4, true));


        // starts off with painting the grid
        Gameboard game = new Gameboard(items);
        game.gameBoard();


        // the game loop
        // ends the game when no zebrarobot remains
        // whenever a cheetah make contact with a zebra, it consumes it
        while (running) {

            // increments the counter
            loopClock++;

            // sends the lists with the counter for movement
            running = game.moveRobot(robots, loopClock);

            // controls the speed of the game
            // else it'll be over in an instant
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {

            }
        }
    }
}
