package com.game;

import java.util.ArrayList;

// class for managing the objects in the game
public class Gameboard {

    // default constructor
    public Gameboard() {
    }

    // constant variable for easy access to grid size
    static final int GRID_SIZE = 20;

    // the gameboard is a 2d String array
    private String[][] gameboard = new String[GRID_SIZE][GRID_SIZE];

    // the tiles in String to print out
    private String defaultTile = " ";
    private String cheetahTile = "C";
    private String zebraTile = "Z";

    // makes every robot move, and replace the old tile with the defaultTile
    public void moveRobot(ArrayList<Robot> robots, int loopClock) {

        // tells the zebras to move every other turn
        for (int i = 0; i < robots.size(); i++) {

            // zebras got enum SLOW = 2, they only move on even loops
            // cheetahs got enum FAST = 1, they move every turn
            if (loopClock % robots.get(i).getSpeed().speed == 0) {

                // overwrites current position with default tile before moving
                gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = defaultTile;

                // may only move if nothing is in the way
                robots.get(i).doRun(gameboard);

                // clock down cheetahs fullness
                robots.get(i).setFullness(robots.get(i).getFullness() - 1);

                // instaceof compares ojects and returns true if both are the same
                // cover current position with corresponding tile
                if (robots.get(i) instanceof ZebraRobot) {
                    gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = zebraTile;
                } else if (robots.get(i) instanceof CheetahRobot) {

                    // checks current cheetah if it stands on
                    // same tile as a zebra
                    if (gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()].equals("Z")) {

                        ((CheetahRobot) robots.get(i)).eatZebra(robots);
                    }
                    gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = cheetahTile;
                }
            }
        }
    }

    // nestled for-loop for printing the gameboard in the terminal
    // prints a frame surrounding the field
    public void printBoard() {
        System.out.println("-------------------------------------------");
        for (int i = 0; i < gameboard.length; i++) {

            System.out.print("|");
            for (int j = 0; j < gameboard.length; j++) {
                System.out.printf(" %s", gameboard[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("-------------------------------------------");
        System.out.println();
    }

    // fills the gameboard with the defaultTile in a nestled for-loop
    public void fillGameboard() {

        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                gameboard[i][j] = defaultTile;
            }
        }
    }
}