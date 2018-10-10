package com.game;

import java.util.ArrayList;
import java.util.Random;

// subclass from robot
public class CheetahRobot extends Robot {

    private int fullness;
    private Random rnd = new Random();

    // default constructor
    // places the robot on the lower right tile
    // speed is set to fast, hungerstate is by default hungry
    public CheetahRobot() {
        super(Gameboard.GRID_SIZE - 1, Gameboard.GRID_SIZE - 1);
        setSpeed(Speed.FAST);
        fullness = 0;
    }

    // constructor for custom placement of the robot
    // speed is set to fast, hungerstate is by default hungry
    public CheetahRobot(int coordX, int coordY) {
        super(coordX, coordY);
        setSpeed(Speed.FAST);
        fullness = 0;
    }

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        if (fullness >= 0) {
            this.fullness = fullness;
        }
    }

    // move the robot when called
    @Override
    public void doRun(String[][] board, ArrayList<Robot> robots) {

        if (fullness == 0) {

            int dX, dY, dZ;
            int closeID = 0;
            int closest = (int) Math.round(Math.sqrt(2 * Gameboard.GRID_SIZE * (Gameboard.GRID_SIZE)));
            boolean clear = false;

            for (int i = 0; i < robots.size(); i++) {
                if (robots.get(i) instanceof ZebraRobot) {
                    dX = robots.get(i).getCoordX() - this.getCoordX();
                    dY = robots.get(i).getCoordY() - this.getCoordY();
                    dZ = (int) Math.round(Math.sqrt((dX * dX) + (dY * dY)));

                    if (dZ < closest) {
                        closest = dZ;
                        closeID = i;
                    }
                }
            }
            dX = robots.get(closeID).getCoordX() - this.getCoordX();
            dY = robots.get(closeID).getCoordY() - this.getCoordY();


            double angle = Math.atan2(dY, dX);


            do {

                if ((this.getCoordX() + (int) Math.round(Math.cos(angle)) >= 0 && this.getCoordX() + (int) Math.round(Math.cos(angle)) < Gameboard.GRID_SIZE)
                        && (this.getCoordY() + (int) Math.round(Math.sin(angle)) >= 0 && this.getCoordY() + (int) Math.round(Math.sin(angle)) < Gameboard.GRID_SIZE)) {

                    if (board[this.getCoordY() + (int) Math.round(1 * Math.sin(angle))][this.getCoordX() + (int) Math.round(1 * Math.cos(angle))] == " C") {

                        angle += Math.PI / 2;

                    } else {

                        setCoordX(this.getCoordX() + (int) Math.round(Math.cos(angle)));
                        setCoordY(this.getCoordY() + (int) Math.round(Math.sin(angle)));

                        clear = true;

                    }
                } else {
                    clear = true;
                }
            } while (!clear);
        }
    }
}
