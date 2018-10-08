package com.game;

import javax.swing.*;
import java.awt.*;

public class GameGui extends JPanel {

    JFrame frame;
    ZebraRobot z;
    CheetahRobot c;

    public void Gameboard() {

    }

    public void createWindow() {
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(520, 540);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(this);
        frame.setVisible(true);

    }

    public void test() {
        z = new ZebraRobot();
        c = new CheetahRobot();
        boolean test = false;
        while (test == false) {
            frame.getContentPane().repaint();
            System.out.println(z.getCoordX() + " " + z.getCoordY());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }

    }

    public void paintComponent(Graphics g) {
        //draw on g here e.g.
        super.paintComponent(g);
        int test = 0;
        while (test != 500) {
            for (int i = 0; i < 500; i += 50) {
                g.drawRect(test, i, 50, 50);
            }
            test += 50;
        }
        //
        // System.out.println(z.getCoordX());
        g.drawString("G", z.getCoordX() + 25, z.getCoordY() + 25);
        g.drawString("Z", c.getCoordX() + 25, c.getCoordY() + 25);

    }

}
