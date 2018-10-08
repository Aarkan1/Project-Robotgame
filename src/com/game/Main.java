package com.game;

public class Main {

    public static void main(String[] args) {



        // creates a game object and starts the game
        Game game = new Game();

        game.gameloop();




//        GameGui gameGui = new GameGui();
//        gameGui.createWindow();
//        gameGui.test();


    }
}



/*
 * 18.10.06
 * Har lagt till lista med robotar, och fixat en loop där alla objekt rör sig.
 * Lagt till enum Speed.
 * Gjort att Cheetahs går vart varv, Zebror vartannat varv == Cheetahs är dubbelt så snabba
 * Man kan nu enkelt ändra storleken på spelplanen, och inget går OutOfBounds
 *
 *
 * 18.10.07
 * Lagt till kommentarer på alla klasser och funktioner
 * Cheetahs äter nu zebror när de kolliderar
 * Spelet avslutas när zebrorna är slut
 * Lagt till kollisions check, så robotarna inte spawnar på samma ruta
 * och att de inte går på varandra när de går runt
 *
 * */