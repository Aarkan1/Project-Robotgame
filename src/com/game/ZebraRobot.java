package com.game;

import java.util.ArrayList;
import java.util.Random;

// subclass from robot
public class ZebraRobot extends Robot {

    Random rnd = new Random();

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
    public void doRun(String[][] board, ArrayList<Robot> robots) {
//        super.doRun(board, robots);


        int dX, dY, dZ;
        int closeID = 0;
        int closest = (int) Math.round(Math.sqrt(2 * Gameboard.GRID_SIZE * Gameboard.GRID_SIZE));
        boolean clear = false;

        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i) instanceof CheetahRobot) {
                dX = robots.get(i).getCoordX() - this.getCoordX();
                dY = robots.get(i).getCoordY() - this.getCoordY();
                dZ = (int) Math.round(Math.sqrt((dX * dX) + (dY * dY)));

                if (dZ < closest) {
                    closest = dZ;
                    closeID = i;
                }
            }
        }
        dX = robots.get(closeID).getCoordX() - this.getCoordX();
        dY = robots.get(closeID).getCoordY() - this.getCoordY();


        double angle = Math.atan2(dY, dX) + Math.PI;


        do {

            if ((this.getCoordX() + (int) Math.round(Math.cos(angle)) >= 0 && this.getCoordX() + (int) Math.round(Math.cos(angle)) < Gameboard.GRID_SIZE)
                    && (this.getCoordY() + (int) Math.round(Math.sin(angle)) >= 0 && this.getCoordY() + (int) Math.round(Math.sin(angle)) < Gameboard.GRID_SIZE)) {

                if (board[this.getCoordY() + (int) Math.round(Math.sin(angle))][this.getCoordX() + (int) Math.round(Math.cos(angle))].equals("Z")) {

                    angle += Math.PI / 2;


                } else {

                    setCoordX(this.getCoordX() + (int) Math.round(Math.cos(angle)));
                    setCoordY(this.getCoordY() + (int) Math.round(Math.sin(angle)));

                    clear = true;

                }
            } else {
                clear = true;
            }
        } while (!clear);


    }

}
