package SetGame;

import Characters.Gatherer;
import main.GameWindow;
import main.Input;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * implement game states: pause, resume, quit, and restart
 * keyboard p = pause game, r = resume game, esc = quit game, space = restart
 */
public class gameState {

    Input keyI;
    GameWindow gw;
    public String state;

    /**
     * constructor the game state
     * @param gw: game window
     * @param keyI: keyboard inputs
     */
    public gameState(GameWindow gw, Input keyI){
        this.gw = gw;
        this.keyI = keyI;
        state = "resume";
    }

    /**
     * update the game state
     * keyboard inputs p = pause, r = resume, esc = quit game, space = restart
     */
    public void update(){
        //p = pause state
        if(keyI.pPress){
            state = "pause";
            gw.gamePaused = true;
            gw.gameResumed = false;
        }
        //r = resume state
        if(keyI.rPress){
            state = "resume";
            gw.gameResumed = true;
            gw.gamePaused = false;
        }
        //esc = quit the game
        if(keyI.escPress){
            System.exit(1);
        }
        //space = restart the game
        if(keyI.spPress){
            if(gw.gamePaused == false || gw.gameReset == true) {
                gw.stats.playTime = 0;
                gw.gameLost = false;
                gw.gameWon = false;
                gw.score = 0;
                gw.setUpGame();
                gw.gatherer = new Gatherer(gw, gw.keyI);
                gw.gState = new gameState(gw, gw.keyI);
            }
        }

    }

    /**
     * display showing message when game paused
     * @param g2 the Graphics2D class to draw the showing message
     */
    public void draw(Graphics2D g2){
        String displayMsg;
        int textLength;
        Font displayFont;
        switch(state){
            case "resume":
                break;
            case "pause":
                displayMsg = "PAUSED";
                try {
                    displayFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")).deriveFont(60f);
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")));
                    g2.setFont(displayFont);
                }
                catch(IOException | FontFormatException e)
                {}
                textLength = (int) g2.getFontMetrics().getStringBounds(displayMsg, g2).getWidth();
                int x = gw.scWidth / 2 - textLength / 2;
                int y = gw.scHeight / 2 + gw.tileSize / 2;
                g2.setColor(Color.white);
                g2.drawString(displayMsg, x+5, y+5);
                g2.setColor(Color.black);
                g2.drawString(displayMsg, x, y);
                break;
        }

    }
}
