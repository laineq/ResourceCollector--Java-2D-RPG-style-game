package main;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

class InputTest {
    private GameWindow gw;

    @BeforeEach
    public void setUp()
    {
        gw = new GameWindow();
    }

    @Test
    public void KeyW()  {
        KeyEvent e = new KeyEvent(gw, KeyEvent.KEY_PRESSED, System.nanoTime(), 0, KeyEvent.VK_W);
        gw.keyI.keyPressed(e);
        Assert.assertTrue(gw.keyI.wPress);
        gw.keyI.keyReleased(e);
        Assert.assertFalse(gw.keyI.wPress);
    }

    @Test
    public void KeyA()  {
        KeyEvent e = new KeyEvent(gw, KeyEvent.KEY_PRESSED, System.nanoTime(), 0, KeyEvent.VK_A);
        gw.keyI.keyPressed(e);
        Assert.assertTrue(gw.keyI.aPress);
        gw.keyI.keyReleased(e);
        Assert.assertFalse(gw.keyI.aPress);
    }

    @Test
    public void KeyS()  {
        KeyEvent e = new KeyEvent(gw, KeyEvent.KEY_PRESSED, System.nanoTime(), 0, KeyEvent.VK_S);
        gw.keyI.keyPressed(e);
        Assert.assertTrue(gw.keyI.sPress);
        gw.keyI.keyReleased(e);
        Assert.assertFalse(gw.keyI.sPress);
    }

    @Test
    public void KeyD()  {
        KeyEvent e = new KeyEvent(gw, KeyEvent.KEY_PRESSED, System.nanoTime(), 0, KeyEvent.VK_D);
        gw.keyI.keyPressed(e);
        Assert.assertTrue(gw.keyI.dPress);
        gw.keyI.keyReleased(e);
        Assert.assertFalse(gw.keyI.dPress);
    }

    @Test
    public void KeyP()  {
        KeyEvent e = new KeyEvent(gw, KeyEvent.KEY_PRESSED, System.nanoTime(), 0, KeyEvent.VK_P);
        gw.keyI.keyPressed(e);
        Assert.assertTrue(gw.keyI.pPress);
        gw.keyI.keyReleased(e);
        Assert.assertFalse(gw.keyI.pPress);
    }

    @Test
    public void KeyR()  {
        KeyEvent e = new KeyEvent(gw, KeyEvent.KEY_PRESSED, System.nanoTime(), 0, KeyEvent.VK_R);
        gw.keyI.keyPressed(e);
        Assert.assertTrue(gw.keyI.rPress);
        gw.keyI.keyReleased(e);
        Assert.assertFalse(gw.keyI.rPress);
    }

    @Test
    public void KeyEsc()  {
        KeyEvent e = new KeyEvent(gw, KeyEvent.KEY_PRESSED, System.nanoTime(), 0, KeyEvent.VK_ESCAPE);
        gw.keyI.keyPressed(e);
        Assert.assertTrue(gw.keyI.escPress);
        gw.keyI.keyReleased(e);
        Assert.assertFalse(gw.keyI.escPress);
    }

    @Test
    public void KeySpace()  {
        KeyEvent e = new KeyEvent(gw, KeyEvent.KEY_PRESSED, System.nanoTime(), 0, KeyEvent.VK_SPACE);
        gw.keyI.keyPressed(e);
        Assert.assertTrue(gw.keyI.spPress);
        gw.keyI.keyReleased(e);
        Assert.assertFalse(gw.keyI.spPress);
    }

    @AfterEach
    public void tearDown(){
        gw = null;
    }
}
