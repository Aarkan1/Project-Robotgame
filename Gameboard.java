package com.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gameboard extends JPanel {

    JFrame frame;
    ArrayList<Robot> zebraRobot = new ArrayList<>();
    ArrayList<Robot> cheetaRobot = new ArrayList<>();
    int[][] grid = new int[10][10];


    public void Gameboard() {

    }

    public void gameBoard() { // Creates the window
        frame = new JFrame("Zebras vs Cheetas");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(516, 538);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        //draw on g here e.g.
        super.paintComponent(g);
        ImageIcon cheetahIcon = new ImageIcon(this.getClass()
                .getResource("res/cheetah50.png"));
        ImageIcon zebraIcon = new ImageIcon(this.getClass()
                .getResource("res/zebra50.png"));
        ImageIcon defaultTile = new ImageIcon(this.getClass()
                .getResource("res/default50.png"));

        int test = 0;
        while (test != 500) {  // Loop that creates rectangles for a grid in the frame
            for (int i = 0; i < 500; i += 50) {
                //g.drawRect(test, i, 50, 50);
                defaultTile.paintIcon(this, g, test, i);
            }
            test += 50;
        }
        for (int i = 0; i < zebraRobot.size(); i++) {
            //g.drawString("Z", zebraRobot.get(i).getCoordX() , zebraRobot.get(i).getCoordY() );
            zebraIcon.paintIcon(this, g, zebraRobot.get(i).getCoordX(), zebraRobot.get(i).getCoordY());
        }
        for (int i = 0; i < cheetaRobot.size(); i++) {
            // g.drawString("G", cheetaRobot.get(i).getCoordX(), cheetaRobot.get(i).getCoordY() );
            cheetahIcon.paintIcon(this, g, cheetaRobot.get(i).getCoordX(), cheetaRobot.get(i).getCoordY());
        }
    }

    // makes every robot move
    public void moveRobot(ArrayList<Robot> zebras, ArrayList<Robot> cheetahs, int loopClock) {

        // tells the zebras to move every other turn
        if (loopClock % zebras.get(0).getSpeed().speed == 0) {
            for (int i = 0; i < zebras.size(); i++) {
                do {
                    zebras.get(i).doRun();
                } while (!zebras.get(i).detectCollision(zebras, cheetahs));
                zebraRobot = zebras;
            }
        }

        //tells the cheetahs to move
        for (int i = 0; i < cheetahs.size(); i++) {

            // check if the cheetah is standing on the same
            // coordinate as a zebra.
            // if it do, remove the zebra
            for (int j = 0; j < zebras.size(); j++) {
                if ((zebras.get(j).getCoordX() == cheetahs.get(i).getCoordX()) &&
                        (zebras.get(j).getCoordY() == cheetahs.get(i).getCoordY())) {
                    zebras.remove(j);
                }
            }
            // may only move if nothing is in the way
            // not efficient
            do {
                cheetahs.get(i).doRun();
            } while (!cheetahs.get(i).detectCollision(zebras, cheetahs));
            cheetaRobot = cheetahs;
        }
        frame.getContentPane().repaint();
    }


}