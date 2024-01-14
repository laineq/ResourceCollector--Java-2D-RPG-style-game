package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Resource Run");

        GameWindow gameWindow = new GameWindow();
        window.add(gameWindow);

        window.pack(); // GameWindow will be displayed in the desired size

        window.setLocationRelativeTo(null); //GameWindow displayed at the center of screen
        window.setVisible(true);

        gameWindow.setUpGame();
        gameWindow.startGameRunner();
    }
}
