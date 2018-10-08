package com.game;

// Cheetahs has a hungerstate
public enum Hunger {

    HUNGRY(true),
    FULL(false);

    final boolean hungerState;

    private Hunger(boolean hungerState) {
        this.hungerState = hungerState;
    }


}
