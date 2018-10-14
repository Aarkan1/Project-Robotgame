package com.game;

import java.util.ArrayList;

public interface IfMovement {

    public void doRun();
    // movement methods
    public boolean detectCollision(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs);

}
