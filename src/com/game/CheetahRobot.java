package com.game;

import java.util.ArrayList;
import java.util.Random;

// subclass from robot
public class CheetahRobot extends Robot {

    private int fullness;
    private Random rnd = new Random();

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
    public void doRun(String[][] board, ArrayList<Robot> robots) {

        if (fullness == 0) {

            int dX, dY, dZ;
            int closeID = 0;
            int closest = (int) Math.round(Math.sqrt(2 * Gameboard.GRID_SIZE * (Gameboard.GRID_SIZE)));
            boolean clear = false;

            // loops the list of robots for zebras
            // and search for the closest
            for (int i = 0; i < robots.size(); i++) {
                if (robots.get(i) instanceof ZebraRobot) {
                    dX = robots.get(i).getCoordX() - this.getCoordX();
                    dY = robots.get(i).getCoordY() - this.getCoordY();
                    dZ = (int) Math.round(Math.sqrt((dX * dX) + (dY * dY)));

                    if (dZ < closest) {
                        closest = dZ;
                        closeID = i;
                    }
                }
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

                if ((this.getCoordX() + (int) Math.round(Math.cos(angle)) >= 0 && this.getCoordX() + (int) Math.round(Math.cos(angle)) < Gameboard.GRID_SIZE)
                        && (this.getCoordY() + (int) Math.round(Math.sin(angle)) >= 0 && this.getCoordY() + (int) Math.round(Math.sin(angle)) < Gameboard.GRID_SIZE)) {

                    if (board[this.getCoordY() + (int) Math.round(Math.sin(angle))][this.getCoordX() + (int) Math.round(Math.cos(angle))].equals("C")) {

                        angle += Math.PI / 2;

                    } else {

                        setCoordX(this.getCoordX() + (int) Math.round(Math.cos(angle)));
                        setCoordY(this.getCoordY() + (int) Math.round(Math.sin(angle)));

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
