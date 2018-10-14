package com.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// class for managing the objects in the game
public class Gameboard extends JPanel {

    // constant variable to easy access to grid size
    static final int GRID_SIZE = 17;

    final int WIDTH = GRID_SIZE * 50 + 14;
    final int HEIGHT = GRID_SIZE * 50 + 37;

    // the gameboard is a 2d String array
    private int[][] gameboard = new int[GRID_SIZE][GRID_SIZE];
    private JFrame frame;
    public ArrayList<Item> items;
    private boolean running;

    // the tiles in String
    private final int defaultT = 0;
    private final int cheetahT = 1;
    private final int zebraT = 2;
    private final int treeT = 3;
    private final int cheetahFedT = 4;
    private final int stoneT = 5;

    // default constructor
    public Gameboard() {
    }

    // constructor that adds the items to gameboard
    public Gameboard(ArrayList<Item> items) {
        this.items = items;
    }

    // makes every robot move, and replace the old tile with the defaultTile
    public boolean moveRobot(ArrayList<Robot> robots, int loopClock) {

        running = true;

        // tells the zebras to move every other turn
        for (int i = 0; i < robots.size(); i++) {

            if(robots.get(i) instanceof ZebraRobot) {
                running = true;
            }
            else if (robots.get(i).getTile() == cheetahFedT){
                running = true;
            }

            // controlling what is moving when
            if (loopClock % robots.get(i).getSpeed().speed == 0) {

                gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = defaultT;

                placeItems(items);

                // may only move if nothing is in the way
                robots.get(i).doRun(gameboard, robots, items);

                // replace new position with corresponding tile
                if (robots.get(i) instanceof CheetahRobot) {
                    // checks current cheetah if it stands on
                    // same tile as a zebra
                    if (gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] == zebraT) {

                        ((CheetahRobot) robots.get(i)).eatZebra(robots);
                    }
                    if (gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] == treeT) {

                        ((CheetahRobot) robots.get(i)).setHunt(Hunt.HUNTING);
                        running = false;
                    }
                }
                switch (robots.get(i).getTile()) {
                    case cheetahT:
                        gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = cheetahT;
                        break;
                    case cheetahFedT:
                        gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = cheetahFedT;
                        break;
                    case zebraT:
                        gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = zebraT;
                        break;
                }
            }
        }
        frame.getContentPane().repaint();
        return running;
    }

    // adds items to gameboard
    public void placeItems(ArrayList<Item> items) {

        for (int i = 0; i < items.size(); i++) {
            gameboard[items.get(i).getCoordY()][items.get(i).getCoordX()] = items.get(i).getTile();
        }

    }

    public void gameBoard() { // Creates the window
        frame = new JFrame("Cheetahs vs Zebras");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        //draw on g here e.g.
        super.paintComponent(g);
        ImageIcon cheetahIcon = new ImageIcon(this.getClass()
                .getResource("cheetah50.png"));
        ImageIcon cheetahFedTile = new ImageIcon(this.getClass()
                .getResource("cheetahFed50.png"));
        ImageIcon zebraIcon = new ImageIcon(this.getClass()
                .getResource("zebra50.png"));
        ImageIcon defaultTile = new ImageIcon(this.getClass()
                .getResource("default50.png"));
        ImageIcon treeTile = new ImageIcon(this.getClass()
                .getResource("tree50.png"));
        ImageIcon stoneTile = new ImageIcon(this.getClass()
                .getResource("stone50.png"));


        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                switch (gameboard[i][j]) {
                    case defaultT:
                        defaultTile.paintIcon(this, g, j * 50, i * 50);
                        break;
                    case cheetahT:
                        cheetahIcon.paintIcon(this, g, j * 50, i * 50);
                        break;
                    case zebraT:
                        zebraIcon.paintIcon(this, g, j * 50, i * 50);
                        break;
                    case treeT:
                        treeTile.paintIcon(this, g, j * 50, i * 50);
                        break;
                    case cheetahFedT:
                        cheetahFedTile.paintIcon(this, g, j * 50, i * 50);
                        break;
                    case stoneT:
                        stoneTile.paintIcon(this, g, j * 50, i * 50);
                        break;
                }
            }
        }
    }

}