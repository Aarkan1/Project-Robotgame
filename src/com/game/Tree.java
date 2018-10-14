package com.game;

public class Tree extends Item {

    public Tree() {

        setCoordX(Gameboard.GRID_SIZE / 2);
        setCoordY(Gameboard.GRID_SIZE / 2);
        setCollision(true);

    }

    public Tree(int coordX, int coordY, boolean collision) {
        super(coordX, coordY, collision);
    }

    @Override
    public int getTile(){
        return 3;
    }


}
