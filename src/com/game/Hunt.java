package com.game;

public enum Hunt {

    HUNTING(true),
    RETURN_PRAY(false);

    boolean hunting;

    Hunt(boolean hunting){
        this.hunting = hunting;
    }


}
