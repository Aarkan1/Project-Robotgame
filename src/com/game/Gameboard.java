package com.game;

import java.util.ArrayList;

// class for managing the objects in the game
public class Gameboard {

    // constant variable to easy access to grid size
    static final int GRID_SIZE = 20;

    // the gameboard is a 2d String array
    private String[][] gameboard = new String[GRID_SIZE][GRID_SIZE];

    // the tiles in String
    private String defaultTile = " ";
    private String cheetahTile = "C";
    private String zebraTile = "Z";

    public static final String YELLOW = "\033[0;33m";
    public static final String RESET = "\033[0m";

    // default constructor
    public Gameboard() {
    }

    // makes every robot move, and replace the old tile with the defaultTile
    public void moveRobot(ArrayList<Robot> robots, int loopClock) {

        // tells the zebras to move every other turn
        for (int i = 0; i < robots.size(); i++) {

            // controlling what is moving when
            if (loopClock % robots.get(i).getSpeed().speed == 0) {

                gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = defaultTile;

                // may only move if nothing is in the way
                robots.get(i).doRun(gameboard, robots);

                // reduce fullness every turn, stops at 0
                robots.get(i).setFullness(robots.get(i).getFullness() - 1);

                // replace new position with corresponding tile
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
    public void printBoard() {
        System.out.println("-----------------------------------------------------------------------------------");
        for (int i = 0; i < gameboard.length; i++) {
            System.out.print("|");
            for (int j = 0; j < gameboard.length; j++) {
                switch (gameboard[i][j]) {
                    case " ":
                        System.out.printf("\t%s", defaultTile);
                        break;
                    case "C":
                        System.out.printf("\t%s%s%s", YELLOW,"\uD83D\uDC06", RESET);
                        break;
                    case "Z":
                        System.out.printf("\t%s", "\uD83D\uDC0E");
                        break;
                }
            }
            System.out.printf("\t%s\n", "|");
            System.out.printf("|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s\n", "|");
        }
        System.out.println("-----------------------------------------------------------------------------------");
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