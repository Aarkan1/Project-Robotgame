package com.game;

import java.util.ArrayList;

// subclass from robot
public class CheetahRobot extends Robot {

    // cheetahs has a hungerstate
    Hunger hungerState;

    // default constructor
    // places the robot on the lower right tile
    // speed is set to fast, hungerstate is by default hungry
    public CheetahRobot() {
        super(Gameboard.GRID_SIZE - 1, Gameboard.GRID_SIZE - 1);
        setHungerState(Hunger.HUNGRY);
        setSpeed(Speed.FAST);
    }

    // constructor for custom placement of the robot
    // speed is set to fast, hungerstate is by default hungry
    public CheetahRobot(int coordX, int coordY) {
        super(coordX, coordY);
        setHungerState(Hunger.HUNGRY);
        setSpeed(Speed.FAST);
    }

    public Hunger getHungerState() {
        return hungerState;
    }

    public void setHungerState(Hunger hungerState) {
        this.hungerState = hungerState;
    }

    // move the robot when called
    @Override
    public void doRun(String[][] board) {
        super.doRun(board);
    }

}
