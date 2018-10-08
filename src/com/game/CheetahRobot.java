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
    public void doRun(ArrayList<Robot> zebras) {
        super.doRun(zebras);

        int closestZ = (int) Math.round(Math.sqrt((2 * Gameboard.GRID_SIZE * Gameboard.GRID_SIZE)));
        int dZ, dX = 1, dY = 1;
        int closeIndex = 0;

        if (!zebras.isEmpty()) {
            for (int i = 0; i < zebras.size(); i++) {

                dX = zebras.get(i).getCoordX() - this.getCoordX();
                dY = zebras.get(i).getCoordY() - this.getCoordY();
                dZ = (int) Math.round(Math.sqrt((dX * dX) + (dY * dY)));

                if (dZ < closestZ) {
                    closestZ = dZ;
                    closeIndex = i;
                }

            }

            dX = zebras.get(closeIndex).getCoordX() - this.getCoordX();
            dY = zebras.get(closeIndex).getCoordY() - this.getCoordY();

            double angle = Math.atan2(dY, dX);
            if ((getCoordX() + (int) Math.round(Math.cos(angle)) >= 0) && (getCoordX() + Math.round(Math.cos(angle)) <= Gameboard.GRID_SIZE - 1)) {
                setCoordX(getCoordX() + (int) Math.round(Math.cos(angle)));
            }
            if ((getCoordY() + (int) Math.round(Math.sin(angle)) >= 0) && (getCoordY() + Math.round(Math.sin(angle)) <= Gameboard.GRID_SIZE - 1)) {
                setCoordY(getCoordY() + (int) Math.round(Math.sin(angle)));
            }
        }

    }

    // function for detecting if the next step has an object or not
    // returns true if next step is clear
    @Override
    public boolean detectCollision(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs) {

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
