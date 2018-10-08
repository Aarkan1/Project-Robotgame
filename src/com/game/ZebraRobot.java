package com.game;

import java.util.ArrayList;

// subclass from robot
public class ZebraRobot extends Robot {

    // default constructor
    // places the robot on the upper left tile
    // speed is set to slow
    public ZebraRobot() {
        super(0, 0);
        setSpeed(Speed.SLOW);
    }

    // constructor for custom placement of the robot
    // speed is set to slow
    public ZebraRobot(int coordX, int coordY) {
        super(coordX, coordY);
        setSpeed(Speed.SLOW);
    }

    // move the robot when called
    @Override
    public void doRun(ArrayList<Robot> zebras) {
        super.doRun(zebras);
    }

    // function for detecting if the next step has an object or not
    // returns true if next step is clear
    @Override
    public boolean detectCollision(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs) {

        boolean clear = true;

        for (int i = 0; i < zebras.size(); i++) {
            if (!(zebras.get(i) == this)) {
                if ((zebras.get(i).getCoordX() == this.getCoordX()) && (zebras.get(i).getCoordY() == this.getCoordY())) {
                    clear = false;
                    i = zebras.size();
                }
            }
        }
        return clear;
    }
}
