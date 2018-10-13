package com.game;

import java.util.ArrayList;
import java.util.Random;

public class ZebraRobot extends Robot {

    public ZebraRobot() {
        super(0, 0);
        setSpeed(Speed.SLOW);
    }

    public ZebraRobot(int coordX, int coordY) {
        super(coordX, coordY);
        setSpeed(Speed.SLOW);
    }

    @Override
    public void doRun() {
        super.doRun();
    }

    @Override
    public boolean detectCollision(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs) {

        boolean clear = true;

        for (int i = 0; i < zebras.size(); i++) {
            if (!(zebras.get(i) == this)) {
                if ((zebras.get(i).getCoordX()== this.getCoordX() && (zebras.get(i).getCoordY() == this.getCoordY()))) {
                    clear = false;
                    i = zebras.size();
                }
            }
        }
        return clear;
    }
}
