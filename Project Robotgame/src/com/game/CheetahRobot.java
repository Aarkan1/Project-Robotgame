package com.game;

public class CheetahRobot extends Robot {

    private Hunger hungerState;

    public Hunger getHungerState() {
        return hungerState;
    }

    public void setHungerState(Hunger hungerState) {
        this.hungerState = hungerState;
    }

    public CheetahRobot() {
        super(29, 29);
        hungerState = Hunger.HUNGRY;
        setSpeed(2);
    }

    public CheetahRobot(int coordX, int coordY) {
        super(coordX, coordY);
         hungerState = Hunger.HUNGRY;
        setSpeed(2);
    }

    @Override
    public void doRun(Robot zebra) {
        int dX = zebra.getCoordX() - getCoordX();
        int dY = zebra.getCoordY() - getCoordY();
        double angle = Math.atan2(dY, dX);
        if ((getCoordX() + getSpeed()*(int)Math.round(Math.cos(angle)) >= 0) && (getCoordX() + getSpeed()*Math.round(Math.cos(angle)) <= 29)) {
            setCoordX(getCoordX() + getSpeed()*(int)Math.round(Math.cos(angle)));
        }
        if ((getCoordY() + getSpeed()*(int)Math.round(Math.sin(angle)) >= 0) && (getCoordY() + getSpeed()*Math.round(Math.sin(angle)) <= 29)) {
            setCoordY(getCoordY() + getSpeed() * (int) Math.round(Math.sin(angle)));
        }
    }
}
