package com.game;

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
    public void doRun(String[][] board) {
        super.doRun(board);
    }

}
