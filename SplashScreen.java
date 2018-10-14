package com.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class SplashScreen extends JWindow {
    private BorderLayout borderLayout;
    private JLabel imgLabel;
    private JPanel southPanel;
    private FlowLayout southFlow;
    private JProgressBar progressBar;
    private ImageIcon imgIcon;

    public SplashScreen() {
        this.imgIcon = new ImageIcon(this.getClass().getResource("res/splashScreen2.jpg"));
        borderLayout = new BorderLayout();
        imgLabel = new JLabel();
        southPanel = new JPanel();
        southFlow = new FlowLayout();
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        try {
            initiate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initiate() throws Exception {
        imgLabel.setIcon(imgIcon);
        getContentPane().setLayout(borderLayout);
        southPanel.setLayout(southFlow);
        southPanel.setBackground(Color.BLACK);
        getContentPane().add(imgLabel, BorderLayout.CENTER);
        getContentPane().add(southPanel, BorderLayout.SOUTH);
        southPanel.add(progressBar, null);
        pack();
    }

    public void setMaxProgress(int maxProgress) {
        progressBar.setMaximum(maxProgress);
    }

    public void setProgress(int progress) {
        float precentage = ((float) progress / (float) progressBar.getMaximum() * 100);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                progressBar.setValue(progress);
                progressBar.setString("Loading: " + precentage + "%");
            }
        });
    }

}
