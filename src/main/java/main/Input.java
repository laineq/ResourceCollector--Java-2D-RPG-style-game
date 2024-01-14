package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * class to take keyboard inputs
 * implements the KeyListener
 */
public class Input implements KeyListener {

    public boolean wPress, aPress, sPress, dPress;
    public boolean pPress, rPress, escPress, spPress;
    GameWindow gw;

    /**
     *set game window
     * @param gw: the game window
     */
    public Input(GameWindow gw) {
        this.gw = gw;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * determine if the pressed key is valid
     * @param e: keyboard inputs
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // Returns the integer keyCode of the key pressed

        if(gw.game_state == gw.menu_state) {
            menuKeyPressed(code);
        }
        if (!(gw.gameLost || gw.gameWon)) {
            gameKeyPressed(code);
        }

        if (code == KeyEvent.VK_ESCAPE) {
            escPress = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spPress = true;
        }
    }

    /**
     * determine the action to be performed if a key is pressed
     * @param code: code of keyboard inputs
     */
    public void menuKeyPressed(int code) {
        // Menu state
        if (code == KeyEvent.VK_W) {
            gw.ui.commandNumber--;
            if(gw.ui.commandNumber < 0)
            {
                gw.ui.commandNumber = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gw.ui.commandNumber++;
            if(gw.ui.commandNumber > 1)
            {
                gw.ui.commandNumber = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            if (gw.ui.commandNumber == 0) {
                gw.game_state = gw.play_state;
            }
            if (gw.ui.commandNumber == 1) {
                System.exit(0);
            }
        }
    }

    /**
     * determine the action to be performed if a key is pressed
     * @param code: code of keyboard inputs
     */
    public void gameKeyPressed(int code)
    {
        if (code == KeyEvent.VK_P) {
            pPress = true;
        }
        if (code == KeyEvent.VK_R) {
            rPress = true;
        }
        if (code == KeyEvent.VK_W) {
            wPress = true;
        }
        if (code == KeyEvent.VK_A) {
            aPress = true;
        }
        if (code == KeyEvent.VK_S) {
            sPress = true;
        }
        if (code == KeyEvent.VK_D) {
            dPress = true;
        }
    }
    /**
     * reset status after the pressed key being released
     * @param e: keyboard inputs
     */
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_P) {
            pPress = false;
        }
        if (code == KeyEvent.VK_R) {
            rPress = false;
        }
        if (code == KeyEvent.VK_W) {
            wPress = false;
        }
        if (code == KeyEvent.VK_A) {
            aPress = false;
        }
        if (code == KeyEvent.VK_S) {
            sPress = false;
        }
        if (code == KeyEvent.VK_D) {
            dPress = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPress = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spPress = false;
        }
    }
}
