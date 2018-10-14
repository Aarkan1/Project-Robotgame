package com.game;

import java.util.ArrayList;
import java.util.Random;

// subclass from robot
public class CheetahRobot extends Robot {

    private int fullness;
    private Random rnd = new Random();
    private int closeID;

    // default constructor
    // places the robot on the lower right tile
    // speed is set to fast, hungerstate is by default hungry
    public CheetahRobot() {
        super(Gameboard.GRID_SIZE - 1, Gameboard.GRID_SIZE - 1);
        setSpeed(Speed.FAST);
        fullness = 0;
    }

    // constructor for custom placement of the robot
    // speed is set to fast, hungerstate is by default hungry
    public CheetahRobot(int coordX, int coordY) {
        super(coordX, coordY);
        setSpeed(Speed.FAST);
        fullness = 0;
    }

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        if (fullness >= 0) {
            this.fullness = fullness;
        }
    }

    // move the robot when called

    // with trigonometry the robot gets a purpose
    // instead of setting a random coordinate
    // by dividing the opposite with the adjacent in trigonometry
    // we get the trajectory to the object
    @Override
    public void doRun(int[][] board, ArrayList<Robot> robots) {

        if (fullness == 0) {

            int dX, dY, dZ;
            int closest = (int) Math.round(Math.sqrt(2 * Math.pow(Gameboard.GRID_SIZE, 2)));
            boolean clear = false;
            int sinY, cosX;

            closeID = findClosest(robots, 0, closest);

            if (closeID >= robots.size()) {
                closeID = 0;
            }

            // when the closest zebra is found, we specify it from the list
            // and gets the opposite and adjacent for getting trajectory
            dX = robots.get(closeID).getCoordX() - this.getCoordX();
            dY = robots.get(closeID).getCoordY() - this.getCoordY();

            // get the trajectory to nearest zebra
            double angle = Math.atan2(dY, dX);

            // checks for collision before moving
            // if obstacle, move 90 degrees and try again
            do {

                sinY = this.getCoordY() + (int) Math.round(Math.sin(angle));
                cosX = this.getCoordX() + (int) Math.round(Math.cos(angle));

                if ((cosX >= 0 && cosX < Gameboard.GRID_SIZE)
                        && (sinY >= 0 && sinY < Gameboard.GRID_SIZE)) {

                    if (board[sinY][cosX] == 1) {

                        angle += Math.PI / 2;

                    } else {

                        setCoordX(cosX);
                        setCoordY(sinY);

                        clear = true;
                    }
                }
                // controlling possible infinity loop
                else {
                    clear = true;
                }
            } while (!clear);
        }
    }

    public int findClosest(ArrayList<Robot> robots, int i, int closest) {
        int dX, dY, dZ;

        if (i == robots.size()) {
            return closeID;
        }
        // loops the list of robots for zebras
        // and search for the closest
        else if (robots.get(i) instanceof ZebraRobot) {

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

    // check every zebras position for collision
    // if collision is true the cheetah eats the zebra
    public void eatZebra(ArrayList<Robot> robots) {

        for (int i = 0; i < robots.size(); i++) {

            if (robots.get(i) instanceof ZebraRobot) {
                if ((this.getCoordX() == robots.get(i).getCoordX()) &&
                        (this.getCoordY() == robots.get(i).getCoordY())) {
                    robots.remove(i);

                    // set fullness clock to 5 after dinner
                    setFullness(5);
                }
            }
        }
    }
}
