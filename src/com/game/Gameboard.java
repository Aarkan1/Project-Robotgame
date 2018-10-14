package com.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// class for managing the objects in the game
public class Gameboard extends JPanel{

    // constant variable to easy access to grid size
    static final int GRID_SIZE = 17;

    final int WIDTH = GRID_SIZE * 50 + 14;
    final int HEIGHT = GRID_SIZE * 50 + 37;

    // the gameboard is a 2d String array
    private int[][] gameboard = new int[GRID_SIZE][GRID_SIZE];
    private JFrame frame;

    // the tiles in String
    private int defaultTile = 0;
    private int cheetahTile = 1;
    private int zebraTile = 2;

    // default constructor
    public Gameboard() {
    }

    // makes every robot move, and replace the old tile with the defaultTile
    public void moveRobot(ArrayList<Robot> robots, int loopClock) {

        // tells the zebras to move every other turn
        for (int i = 0; i < robots.size(); i++) {

            // controlling what is moving when
            if (loopClock % robots.get(i).getSpeed().speed == 0) {

                gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = defaultTile;

                // may only move if nothing is in the way
                robots.get(i).doRun(gameboard, robots);

                // reduce fullness every turn, stops at 0
                robots.get(i).setFullness(robots.get(i).getFullness() - 1);

                // replace new position with corresponding tile
                if (robots.get(i) instanceof ZebraRobot) {
                    gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = zebraTile;
                } else if (robots.get(i) instanceof CheetahRobot) {
                    // checks current cheetah if it stands on
                    // same tile as a zebra
                    if (gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] == 2) {

                        ((CheetahRobot) robots.get(i)).eatZebra(robots);
                    }
                    gameboard[robots.get(i).getCoordY()][robots.get(i).getCoordX()] = cheetahTile;
                }
            }
        }
        frame.getContentPane().repaint();
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
        ImageIcon zebraIcon = new ImageIcon(this.getClass()
                .getResource("zebra50.png"));
        ImageIcon defaultTile = new ImageIcon(this.getClass()
                .getResource("default50.png"));


        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                switch (gameboard[i][j]) {
                    case 0:
                        defaultTile.paintIcon(this, g,j * 50 , i * 50);
                        break;
                    case 1:
                        cheetahIcon.paintIcon(this, g, j * 50, i * 50);
                        break;
                    case 2:
                        zebraIcon.paintIcon(this, g, j * 50, i * 50);
                        break;
                }
            }
        }
    }

}