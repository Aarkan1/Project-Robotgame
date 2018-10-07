package com.game;

public class Gameboard {

    final int GAME_SIZE = 30;

    String[][] gameboard = new String[GAME_SIZE][GAME_SIZE];

    String defaultTile = " |";
    String cheetahTile = "C|";
    String zebraTile = "Z|";

    public int getGAME_SIZE(){
        return GAME_SIZE - 1;
    }

    public void gameboard() {

        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                gameboard[i][j] = defaultTile;
            }
        }
    }

    public void spawnRobot(Robot z,Robot c){
        if (z.getCoordX() == c.getCoordX() && z.getCoordY() == c.getCoordY()) {
            ((CheetahRobot) c).setHungerState(Hunger.FULL);
        }
        gameboard[z.getCoordX()][z.getCoordY()] = defaultTile;
        z.doRun(c);
        gameboard[z.getCoordX()][z.getCoordY()] = zebraTile;

        gameboard[c.getCoordX()][c.getCoordY()] = defaultTile;
        c.doRun(z);
        gameboard[c.getCoordX()][c.getCoordY()] = cheetahTile;


    }

    public  void sr(Robot... r){

    }



    public void printBoard() {


        for (int i = 0; i < gameboard.length; i++) {
            System.out.print("|");
            for (int j = 0; j < gameboard.length; j++) {
                System.out.print(gameboard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


}
