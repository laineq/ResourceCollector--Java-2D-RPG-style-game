package main;

import Characters.Gatherer;
import Characters.Scavenger;
import GameTiles.Tile_Manager;
import SetGame.AssetSetter;
import SetGame.gameStat;
import SetGame.gameState;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JPanel implements Runnable {

    int ogTileSize, scale, timeBr, maxScCol, maxScRow;
    double FPS; // int FPS -> double FPS
    public int tileSize, scWidth, scHeight, maxWorldCol, maxWorldRow, worldWidth, worldHeight;
    public int score, game_state, menu_state, play_state, pause_state;
    public boolean gamePaused, gameLost, gameWon, gameResumed, gameReset;
    Tile_Manager background;
    public Input keyI;
    public Thread gameRunner;
    public CollisionChecker cch;
    public AssetSetter aSetter;
    public UI ui;
    public gameStat stats;
    public Gatherer gatherer;
    public Object[] obj;
    public Scavenger[] scavengers;
    public gameState gState;

    public GameWindow() {
        this.setPrimitiveVariables();
        this.setVariables();
        this.setPreferredSize(new Dimension(scWidth, scHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Creates game window in the background via a painting buffer; improves performance
        this.addKeyListener(keyI);
        this.setFocusable(true);
    }

    private void setVariables() {
        background = new Tile_Manager(this);
        keyI = new Input(this);
        cch = new CollisionChecker(this);
        aSetter = new AssetSetter(this);
        ui = new UI(this);

        stats = new gameStat(this);
        gatherer = new Gatherer(this, keyI);
        obj = new Object[16];
        scavengers = new Scavenger[5];
        gState = new gameState(this, keyI);
    }

    private void setPrimitiveVariables() {
        ogTileSize = 32; // 32x32 size tiles
        scale = 2;
        timeBr = 0;

        tileSize = ogTileSize * scale; // 64x64 tile size
        maxScCol = 20;
        maxScRow = 15;
        scWidth = tileSize * maxScCol; // 1280 pixels
        scHeight = tileSize * maxScRow; // 960 pixels

        maxWorldCol = 70;
        maxWorldRow = 70;

        worldWidth = tileSize * maxWorldCol;
        worldHeight = tileSize * maxWorldRow;

        FPS = 30;

        score = 0;

        gameLost = false;
        gameWon = false;
        gamePaused = false;
        gameResumed = false;
        gameReset = false;

        // Game states
        menu_state = 0;
        play_state = 1;
        pause_state = 2;
    }

    public void setUpGame()
    {
        aSetter.setObject();
        aSetter.setEnemy();
    }

    public void startGameRunner(){
        gameRunner = new Thread(this); // this = GameWindow class
        gameRunner.start();

    }

    @Override
    public void run() {
        double makeinterval = 1000000000 / FPS; // 1B nanoseconds/30 FPS (0.03334 seconds)
        double nextdrawing = System.nanoTime() + makeinterval;

        while (gameRunner != null) {

            try{
                if(gamePaused){ // Simplified if statements (if(gamePaused == true) -> if(gamePaused))
                    gState.update();
                }
                else {
                    update(); // Updates information (resource position, character position, etc)
                    repaint(); // Displays screen with updated information
                }

                double remaindertime = nextdrawing - System.nanoTime();
                remaindertime = remaindertime / 1000000; //Thread.sleep only accepts time in ms; convert remaindertime from ns to ms

                if (remaindertime < 0) {
                    remaindertime = 0;
                }

                Thread.sleep((long) remaindertime); // Thread.sleep() in a loop which results in busy waiting -> necessary for the purpose of the method

                nextdrawing += makeinterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g){ // Graphics class allows us to create objects and display them on the screen

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // Extends the Graphics class for greater functionality with 2D geometry and coordinates

        if(game_state == menu_state)
        {
            ui.draw(g2);
        }
        else
        {
            background.draw(g2);

            for (Object object : obj) { // Enhanced for loop
                if (object != null) {
                    object.draw(g2, this);
                }
            }

            for (Scavenger scavenger : scavengers) {
                if (scavenger != null) {
                    scavenger.draw(g2);
                }
            }
            try {
                gatherer.draw(g2);
            } catch (IOException | FontFormatException e) { // Collapsed catch blocks
                e.printStackTrace();
            }

            try {
                stats.draw(g2);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            gState.draw(g2);

            ui.draw(g2);
        }

        g2.dispose(); // Gets rid of the current iteration of the graphics and releases system resources which saves memory (like refresh function)
    }
    public void update(){
        boolean ifMoved = gatherer.update();
        if(ifMoved) {
            int gathererX = gatherer.getX();
            int gathererY = gatherer.getY();
            for (Scavenger scavenger : scavengers) {
                scavenger.update(gathererX, gathererY);
            }
        }

        //make bonus resource rewards disappear after a while
        if(stats.playTime>20){
            this.obj[14] = null;
            this.obj[4] = null;
        }
        gState.update();
    }
}
