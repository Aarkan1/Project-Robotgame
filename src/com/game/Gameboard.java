package com.game;

import java.util.ArrayList;

// class for managing the objects in the game
public class Gameboard {

    // default constructor
    public Gameboard() {
    }

    // constant variable to easy access to grid size
    static final int GRID_SIZE = 20;

    // the gameboard is a 2d String array
    private String[][] gameboard = new String[GRID_SIZE][GRID_SIZE];

    // the tiles in String
    private String defaultTile = " ";
    private String cheetahTile = "C";
    private String zebraTile = "Z";

    // fills the gameboard with the defaultTile in a nestled for-loop
    public void gameboard() {

        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                gameboard[i][j] = defaultTile;
            }
        }
    }

    // makes every robot move, and replace the old tile with the defaultTile
    public void moveRobot(ArrayList<Robot> robots, int loopClock) {

        // tells the zebras to move every other turn
        for (int i = 0; i < robots.size(); i++) {

            if (loopClock % robots.get(i).getSpeed().speed == 0) {

                gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = defaultTile;

                // may only move if nothing is in the way
                robots.get(i).doRun(gameboard);

                // clock down cheetahs fullness
                robots.get(i).setFullness(robots.get(i).getFullness() - 1);

                // instaceof compares ojects and returns true if both are the same
                if (robots.get(i) instanceof ZebraRobot) {
                    gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = zebraTile;
                } else if (robots.get(i) instanceof CheetahRobot) {
                    gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = cheetahTile;

                    // checks current cheetah if it stands on
                    // same coordinate as a zebra
                    for (int j = 0; j < robots.size(); j++) {

                        if (robots.get(j) instanceof ZebraRobot) {
                            if ((robots.get(i).getCoordX() == robots.get(j).getCoordX()) &&
                                    (robots.get(i).getCoordY() == robots.get(j).getCoordY())) {
                                robots.remove(j);

                                // set fullness clock to 5 after dinner
                                robots.get(i).setFullness(5);
                            }
                        }
                    }
                }
            }
        }
    }

    // nestled for-loop for printing the gameboard in the terminal
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
}