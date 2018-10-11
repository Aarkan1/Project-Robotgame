package com.game;

import java.util.ArrayList;
import java.util.Random;

// superclass, a Robot template
public abstract class Robot implements IfMovement {

    // positioning and speed variables
    private int coordX, coordY;
    private Speed speed;
    private int fullness;
    private Random rnd = new Random();


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

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
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
    public void doRun(String[][] gameBoard, ArrayList<Robot> robots) {

        boolean clear = false;

        do {

            double angle = rnd.nextInt(4) * (Math.PI / 2);


            if ((this.getCoordX() + (int) Math.round(Math.cos(angle)) >= 0 && this.getCoordX() + (int) Math.round(Math.cos(angle)) < Gameboard.GRID_SIZE)
                    && (this.getCoordY() + (int) Math.round(Math.sin(angle)) >= 0 && this.getCoordY() + (int) Math.round(Math.sin(angle)) < Gameboard.GRID_SIZE)) {

                if (gameBoard[this.getCoordY() + (int) Math.round(Math.sin(angle))][this.getCoordX() + (int) Math.round(Math.cos(angle))].equals(" ")) {

                    setCoordX(this.getCoordX() + (int) Math.round(Math.cos(angle)));
                    setCoordY(this.getCoordY() + (int) Math.round(Math.sin(angle)));
                    clear = true;
                }
            }
        } while (!clear);

    }

}
