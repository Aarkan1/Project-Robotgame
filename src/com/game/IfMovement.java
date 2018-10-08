package com.game;

import java.util.ArrayList;

// interface for movement
public interface IfMovement {

    // movement methods
    public void doRun(ArrayList<Robot> zebras);
    public boolean detectCollision(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs);

}
