package com.game;

public abstract class Item {

    private int coordX, coordY;
    private boolean collision;

    public Item() {
    }

    public Item(int coordX, int coordY, boolean collision) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.collision = collision;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        if ((coordX >= 0) && (coordX < Gameboard.GRID_SIZE)) {
            this.coordX = coordX;
        }
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        if ((coordY >= 0) && (coordY < Gameboard.GRID_SIZE)) {
            this.coordY = coordY;
        }
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getTile(){
        return 0;
    }


}
