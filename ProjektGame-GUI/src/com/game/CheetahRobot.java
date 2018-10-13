package com.game;

import java.util.ArrayList;

public class CheetahRobot extends Robot {

    public CheetahRobot() {
        super(450, 450);
        Hunger hungerState = Hunger.HUNGRY;
        setSpeed(Speed.FAST);
    }

    public CheetahRobot(int coordX, int coordY) {
        super(coordX, coordY);
        Hunger hungerState = Hunger.HUNGRY;
        setSpeed(Speed.FAST);
    }


    @Override
    public void doRun() {
        super.doRun();

    }

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
