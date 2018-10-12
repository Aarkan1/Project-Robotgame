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

    // with trigonometry the robot gets a purpose
    // instead of setting a random coordinate
    // by dividing the opposite with the adjacent in trigonometry
    // we get the trajectory to the object
    @Override
    public void doRun(String[][] board, ArrayList<Robot> robots) {

        int dX, dY, dZ;
        int closeID = 0;
        int closest = (int) Math.round(Math.sqrt(2 * Gameboard.GRID_SIZE * Gameboard.GRID_SIZE));
        boolean clear = false;
        int cosX, sinY;

        // loops the list of robots for cheetah
        // and search for the closest
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
        // when the closest cheetah is found, we specify it from the list
        // and gets the opposite and adjacent for getting trajectory
        dX = robots.get(closeID).getCoordX() - this.getCoordX();
        dY = robots.get(closeID).getCoordY() - this.getCoordY();


        // the zebra wants to flee from the cheetah
        // so we add 180 degrees to the trajectory
        double angle = Math.atan2(dY, dX) + Math.PI;

        // checks for collision before moving
        // if obstacle, move 90 degrees and try again
        do {

            sinY = this.getCoordY() + (int) Math.round(Math.sin(angle));
            cosX = this.getCoordX() + (int) Math.round(Math.cos(angle));

            if (cosX >= 0 && cosX < Gameboard.GRID_SIZE && sinY >= 0 && sinY < Gameboard.GRID_SIZE) {

                if (board[sinY][cosX].equals("Z")) {

                    angle += Math.PI / 2;


                } else {

                    setCoordX(cosX);
                    setCoordY(sinY);

                    clear = true;

                }
            }
            // a zebra never stands still
            else {

                int rndAngle = rnd.nextInt(2);

                switch (rndAngle) {
                    case 0:
                        angle -= Math.PI / 2;
                        break;
                    case 1:
                        angle += Math.PI / 2;
                        break;
                }

                sinY = this.getCoordY() + (int) Math.round(Math.sin(angle));
                cosX = this.getCoordX() + (int) Math.round(Math.cos(angle));

                if ((cosX >= 0 && cosX < Gameboard.GRID_SIZE) && (sinY >= 0 && sinY < Gameboard.GRID_SIZE) && board[sinY][cosX].equals("Z")) {

                    clear = true;
                } else {

                    if ((cosX >= 0 && cosX < Gameboard.GRID_SIZE) && (sinY >= 0 && sinY < Gameboard.GRID_SIZE)) {

                        setCoordX(cosX);
                        setCoordY(sinY);
                    }
                    clear = true;
                }
            }

        } while (!clear);


    }

}
