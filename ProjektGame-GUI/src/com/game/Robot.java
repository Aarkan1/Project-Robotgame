package com.game;

import java.util.ArrayList;
import java.util.Random;

public abstract class Robot implements IfMovement {

    private int coordX, coordY;
    private Speed speed;

    public Robot() {
        setSpeed(Speed.FAST);
    }

    public Robot(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        setSpeed(Speed.FAST);
    }

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

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }
    public Speed getSpeed() {
        return speed;
    }

    @Override
    public void doRun() {
        Random rnd = new Random();
        Gameboard border = new Gameboard();
        int rndNum;

        rndNum = rnd.nextInt(3) - 1;

        if ((getCoordX() + rndNum >= 0) && (getCoordX() + rndNum <= 450)) {
            setCoordX(getCoordX() + rndNum * 50);
        }

        rndNum = rnd.nextInt(3) - 1;

        if ((getCoordY() + rndNum >= 0) && (getCoordY() + rndNum <= 450)) {
            setCoordY(getCoordY() + rndNum * 50);
        }
    }

    @Override
    public boolean detectCollision(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs) {
        boolean clear = true;

        return clear;
    }

}

