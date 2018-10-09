package com.game;

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
    public void doRun(String[][] board) {

        if (fullness == 0) {

            int rndX, rndY;
            boolean clear = false;

            do {
                rndX = rnd.nextInt(3) - 1;
                rndY = rnd.nextInt(3) - 1;

                if ((this.getCoordX() + rndX >= 0 && this.getCoordX() + rndX < Gameboard.GRID_SIZE)
                        && (this.getCoordY() + rndY >= 0 && this.getCoordY() + rndY < Gameboard.GRID_SIZE)) {
                    if (board[this.getCoordY() + rndY][this.getCoordX() + rndX] != "C|") {
                        clear = true;
                    }
                }

            } while (!clear);

            setCoordX(this.getCoordX() + rndX);
            setCoordY(this.getCoordY() + rndY);
        }

    }

}
