package com.game;


import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public void gameloop() {


        Scanner Scan = new Scanner(System.in);
        int numZe = 0, numGe = 0;
        String str = "";
        boolean inputNum = false;

        do{
            System.out.println("Ange hur många geparder vill du ha? ");
            str = Scan.nextLine();
            if(str.matches("[1-9][0-9]")){
                numGe = Integer.valueOf(str);
                inputNum = true;
            } else {
                System.out.println("This is not allowed!");

            }}while (inputNum == false);
        inputNum= false;
        do{
            System.out.println("Ange hur många zebras vill du ha? ");
            str = Scan.nextLine();
            if(str.matches("[1-9][0-9]")){
                numZe = Integer.valueOf(str);
                inputNum = true;
            } else {
                System.out.println("This is not allowed!");

            }}while ((numZe < numGe) || (inputNum == false));

        // starts off with painting the grid
        Gameboard game = new Gameboard();
        game.gameBoard();

        // counter for enabling turnbased movement
        int loopClock = 1;

        // creates two lists for holding the different robot objects
        ArrayList<Robot> zebras = new ArrayList<>();
        ArrayList<Robot> cheetahs = new ArrayList<>();

        // fills lists with robot objects
        for (int i = 0; i < numZe; i++) {
            zebras.add(new ZebraRobot());

        }
        for (int i = 0; i < numGe; i++) {
            cheetahs.add(new CheetahRobot());
        }

        // the game loop
        // ends the game when no zebrarobot remains
        // whenever a cheetah make contact with a zebra, it consumes it
        while (!zebras.isEmpty()) {

            // increments the counter
            loopClock++;

            // sends the lists with the counter for movement
            game.moveRobot(zebras, cheetahs, loopClock);

            // controls the speed of the game
            // else it'll be over in an instant
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }
}