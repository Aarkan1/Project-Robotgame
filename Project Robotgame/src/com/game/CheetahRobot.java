package com.game;

import java.util.ArrayList;

// subclass from robot
public class CheetahRobot extends Robot {

    // cheetahs has a hungerstate
    Hunger hungerState;
    private int fullness;


    // default constructor
    // places the robot on the lower right tile
    // speed is set to fast, hungerstate is by default hungry
    public CheetahRobot() {
        super(Gameboard.GRID_SIZE - 1, Gameboard.GRID_SIZE - 1);
        setHungerState(Hunger.HUNGRY);
        setSpeed(Speed.FAST);
        setFullness(10);
    }

    // constructor for custom placement of the robot
    // speed is set to fast, hungerstate is by default hungry
    public CheetahRobot(int coordX, int coordY) {
        super(coordX, coordY);
        setHungerState(Hunger.HUNGRY);
        setSpeed(Speed.FAST);
        setFullness(10);
    }
    
    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public Hunger getHungerState() {
        return hungerState;
    }

    public void setHungerState(Hunger hungerState) {
        this.hungerState = hungerState;
    }

    // move the robot when called
    @Override
    public void doRun() {
        super.doRun();
    }

    // function for detecting if the next step has an object or not
    // returns true if next step is clear
    @Override
    public boolean detectCollision(ArrayList<ZebraRobot> zebras, ArrayList<CheetahRobot> cheetahs) {

        boolean clear = true;

        for (int j = 0; j < cheetahs.size(); j++) {
            if (cheetahs.get(j) != this) {
                if ((cheetahs.get(j).getCoordX() == this.getCoordX()) && (cheetahs.get(j).getCoordY() == this.getCoordY())) {
                    clear = false;
                    j = cheetahs.size();
                }
            }
        }
        return clear;
    }
}
