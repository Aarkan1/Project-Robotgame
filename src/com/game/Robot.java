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

        if ((coordX >= 0) && (coordX < Gameboard.GRID_SIZE)) {
            this.coordX = coordX;
        }
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {

        if ((coordY >= 0) && (coordY < Gameboard.GRID_SIZE)) {
            this.coordY = coordY;
        }
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
    // checks if it's a default tile, else it goes another way
    @Override
    public void doRun(String[][] gameBoard) {

        Random rnd = new Random();

        int rndX, rndY;
        boolean clear = false;

        do {
            rndX = rnd.nextInt(3) - 1;
            rndY = rnd.nextInt(3) - 1;

            if ((this.getCoordX() + rndX >= 0 && this.getCoordX() + rndX < Gameboard.GRID_SIZE)
                    && (this.getCoordY() + rndY >= 0 && this.getCoordY() + rndY < Gameboard.GRID_SIZE)) {
                if (gameBoard[this.getCoordY() + rndY][this.getCoordX() + rndX] == " |") {
                    clear = true;
                }
            }

        } while (!clear);

        setCoordX(this.getCoordX() + rndX);
        setCoordY(this.getCoordY() + rndY);

    }

}
