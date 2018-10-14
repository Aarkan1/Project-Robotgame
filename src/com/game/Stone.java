package com.game;

public class Stone extends Item {

    public Stone() {

        setCoordX(Gameboard.GRID_SIZE / 2);
        setCoordY(Gameboard.GRID_SIZE / 2);
        setCollision(true);

    }

    public Stone(int coordX, int coordY, boolean collision) {
        super(coordX, coordY, collision);
    }

    @Override
    public int getTile(){
        return 5;
    }


}
