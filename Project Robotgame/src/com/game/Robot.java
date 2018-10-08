package com.game;

import java.util.ArrayList;
import java.util.Random;

// superclass, a Robot template
public abstract class Robot implements IfMovement {

    // positioning and speed variables
    private int coordX, coordY;
    private Speed speed;

    // default constructor
    public Robot() {
        setSpeed(Speed.FAST);
    }

    // constructor with coordinates as parameters
    public Robot(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        setSpeed(Speed.FAST);
    }

    // getters and setters
    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    // function from the interface
    // when called, makes the robot move 1 step in a random direction
    // checks every step so the robot can't go out of bounds of the grid
    @Override
    public void doRun() {

        Random rnd = new Random();
        Gameboard border = new Gameboard();
        int rndNum;

        rndNum = rnd.nextInt(3) - 1;

        if ((getCoordX() + rndNum >= 0) && (getCoordX() + rndNum <= border.GRID_SIZE - 1)) {
            setCoordX(getCoordX() + rndNum);
        }

        rndNum = rnd.nextInt(3) - 1;

        if ((getCoordY() + rndNum >= 0) && (getCoordY() + rndNum <= border.GRID_SIZE - 1)) {
            setCoordY(getCoordY() + rndNum);
        }
    }


    // function for detecting if the next step has an object or not
    // returns true if next step is clear
    @Override
    public boolean detectCollision(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs) {

        boolean clear = true;

        return clear;
    }

}
