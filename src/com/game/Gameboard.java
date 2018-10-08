package com.game;

import java.util.ArrayList;


public class Gameboard {

    // constant variable to easy access to grid size
    static final int GRID_SIZE = 50;

    // the gameboard is a 2d String array
    String[][] gameboard = new String[GRID_SIZE][GRID_SIZE];

    // the tiles in String
    String defaultTile = " |";
    String cheetahTile = "C|";
    String zebraTile = "Z|";


    // fills the gameboard with the defaultTile in a nestled for-loop
    public void gameboard() {

        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                gameboard[i][j] = defaultTile;
            }
        }
    }

    // makes every robot move, and replace the old tile with the defaultTile
    public void moveRobot(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs, int loopClock) {

        if(!zebras.isEmpty()) {
            // tells the zebras to move every other turn
            if (loopClock % zebras.get(0).getSpeed().speed == 0) {
                for (int i = 0; i < zebras.size(); i++) {
                    gameboard[zebras.get(i).getCoordX()][zebras.get(i).getCoordY()] = defaultTile;

                    // may only move if nothing is in the way
                    // not efficient
                    do {
                        zebras.get(i).doRun(cheetahs);
                    } while (!zebras.get(i).detectCollision(zebras, cheetahs));

                    gameboard[zebras.get(i).getCoordX()][zebras.get(i).getCoordY()] = zebraTile;
                }
            }
        }

        // tells the cheetahs to move
        for (int i = 0; i < cheetahs.size(); i++) {

            // check if the cheetah is standing on the same
            // coordinate as a zebra.
            // if it do, remove the zebra
            for (int j = 0; j < zebras.size(); j++) {
                if ((zebras.get(j).getCoordX() == cheetahs.get(i).getCoordX()) &&
                        (zebras.get(j).getCoordY() == cheetahs.get(i).getCoordY())) {
                    zebras.remove(j);
                }
            }
            gameboard[cheetahs.get(i).getCoordX()][cheetahs.get(i).getCoordY()] = defaultTile;

            // may only move if nothing is in the way
            // not efficient
            do {
                cheetahs.get(i).doRun(zebras);
            } while (!cheetahs.get(i).detectCollision(zebras, cheetahs));

            gameboard[cheetahs.get(i).getCoordX()][cheetahs.get(i).getCoordY()] = cheetahTile;
        }
    }

    // nestled for-loop for printing the gameboard in the terminal
    public void printBoard() {

        for (int i = 0; i < gameboard.length; i++) {
            System.out.print("|");
            for (int j = 0; j < gameboard.length; j++) {
                System.out.printf("%s", gameboard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}