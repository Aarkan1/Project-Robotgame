package com.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private JPanel panel, helperPanel;
    private JButton okButton;
    private JTextField zebraInput, cheetaInput;
    private JLabel label1, label2, error;


    public Menu() {
        setTitle("Zebras vs Cheetas");
        okButton = new JButton("Start!");
        okButton.addActionListener(this);

        zebraInput = new JTextField();
        cheetaInput = new JTextField();

        panel = new JPanel();
        helperPanel = new JPanel();

        label1 = new JLabel("Enter how many Cheetas you want");
        label2 = new JLabel("Enter how many Zebras you want");
        error = new JLabel();

        zebraInput.setMaximumSize(new Dimension(50, 20));
        cheetaInput.setMaximumSize(new Dimension(50, 20));

    }

    public void showMenu() {
        helperPanel.setLayout(new BoxLayout(helperPanel, BoxLayout.PAGE_AXIS));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setSize(500, 500);
        helperPanel.add(label1);
        helperPanel.add(cheetaInput);
        helperPanel.add(label2);
        helperPanel.add(zebraInput);
        helperPanel.add(okButton);
        helperPanel.add(error);
        panel.add(helperPanel);
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int numGe = 0, numZe = 0, startGame = 0;
                if (cheetaInput.getText().matches("[1-9][0-9]*")) {
                    cheetaInput.setBackground(Color.white);
                    numGe = Integer.parseInt(cheetaInput.getText());
                    startGame++;
                } else {
                    error.setText("Only numbers is allowed");
                    cheetaInput.setBackground(Color.RED);
                }
                if (zebraInput.getText().matches("[1-9][0-9]*")) {
                    zebraInput.setBackground(Color.white);
                    numZe = Integer.parseInt(zebraInput.getText());
                    startGame++;
                } else {
                    error.setText("Only numbers is allowed");
                    zebraInput.setBackground(Color.RED);
                }
                if (startGame == 2) {
                    runGame(numGe,numZe);
                }
            }
        });
        thread.start();

    }

    public void runGame(int numGe,int numZe) {
        setVisible(false);
        dispose();
        Game game = new Game();
        game.gameloop(numGe,numZe);
    }
}
