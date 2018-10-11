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
    // check every step that the robot is inside the grid
    // and check for collisions with other objects
    @Override
    public void doRun(String[][] board) {

        // only moves when hungry
        if (fullness == 0) {

            int rndX, rndY;
            boolean clear = false;

            // if obstacle, loop and moves another way
            do {
                rndX = rnd.nextInt(3) - 1;
                rndY = rnd.nextInt(3) - 1;

                if ((this.getCoordX() + rndX >= 0 && this.getCoordX() + rndX < Gameboard.GRID_SIZE)
                        && (this.getCoordY() + rndY >= 0 && this.getCoordY() + rndY < Gameboard.GRID_SIZE)) {
                    if (!board[this.getCoordY() + rndY][this.getCoordX() + rndX].equals("C")) {
                        clear = true;
                    }
                }

            } while (!clear);

            setCoordX(this.getCoordX() + rndX);
            setCoordY(this.getCoordY() + rndY);
        }

    }

    // check every zebras position for collision
    // if collision is true the cheetah eats the zebra
    public void eatZebra(ArrayList<Robot> robots){

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
