package com.game;

import javax.swing.*;

public class SplashScreenDriver {
    private SplashScreen screen;

    public SplashScreenDriver() {
        screen = new SplashScreen();
        screen.setLocationRelativeTo(null);
        screen.setMaxProgress(100);
        screen.setVisible(true);

        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j < 5000; j++) {
                System.out.println("Loading");
            }
            screen.setProgress(i);
        }
        screen.setVisible(false);
    }
}
