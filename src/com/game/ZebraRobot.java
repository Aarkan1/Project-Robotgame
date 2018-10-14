package com.game;

import java.util.ArrayList;
import java.util.Random;

// subclass from robot
public class ZebraRobot extends Robot {

    private Random rnd = new Random();
    private int closeID;

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
    @Override
    public int getTile(){
        return 2;
    }

    // move the robot when called

    // with trigonometry the robot gets a purpose
    // instead of setting a random coordinate
    // by dividing the opposite with the adjacent in trigonometry
    // we get the trajectory to the object
    @Override
    public void doRun(int[][] board, ArrayList<Robot> robots,ArrayList<Item> items) {

        int dX, dY, dZ;
        int closest = (int) Math.round(Math.sqrt(2 * Math.pow(Gameboard.GRID_SIZE, 2)));
        boolean clear = false;
        int cosX, sinY;

        closeID = findClosest(robots, 0, closest);

        if (closeID >= robots.size()){
            closeID = 0;
        }

        // when the closest zebra is found, we specify it from the list
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

                if (board[sinY][cosX] != 0) {

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

                if ((cosX >= 0 && cosX < Gameboard.GRID_SIZE) && (sinY >= 0 && sinY < Gameboard.GRID_SIZE) && board[sinY][cosX] == 2) {

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

    public int findClosest(ArrayList<Robot> robots, int i, int closest) {
        int dX, dY, dZ;

        if (i == robots.size()) {
            return closeID;
        }
        // loops the list of robots for cheetahs
        // and search for the closest
        else if (robots.get(i) instanceof CheetahRobot) {

            dX = robots.get(i).getCoordX() - this.getCoordX();
            dY = robots.get(i).getCoordY() - this.getCoordY();
            dZ = (int) Math.round(Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)));

            if (dZ < closest) {
                closest = dZ;
                closeID = i;
            }
        }
        if (i < robots.size()) {

            findClosest(robots, i + 1, closest);
        }
        return closeID;
    }

}
