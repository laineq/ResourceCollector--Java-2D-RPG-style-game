package main;

import java.awt.*;

/**
 * Class for the objects on the maze: rewards, bonus rewards, traps and end tile
 */
public class Object {
    //member variables for the image, name, coordinates and points of the object
    public Image image;
    public String name;
    public int worldX, worldY;
    public int points;
    
    //sets the default collision to false
    public boolean collision = false;

    //creats a solid rectangle around the object to avoid collision
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    /**
     * Draws the object on the game window
     * @param g2 - the Graphics2D class to draw the sprite of the gatherer
     * @param gw - game window on which the object will appear on the screen
     */
    public void draw(Graphics2D g2, GameWindow gw){
        //sets the coordinates according to the screen displayed on the window
        int screenX = worldX - gw.gatherer.worldX + gw.gatherer.screenX;
        int screenY = worldY - gw.gatherer.worldY + gw.gatherer.screenY;

        //draws the object only when it appears on the game window
        if(worldX + gw.tileSize > gw.gatherer.worldX - gw.gatherer.screenX &&
                worldX - gw.tileSize < gw.gatherer.worldX + gw.gatherer.screenX &&
                worldY + gw.tileSize > gw.gatherer.worldY - gw.gatherer.screenY &&
                worldY - gw.tileSize < gw.gatherer.worldY + gw.gatherer.screenY) {

            g2.drawImage(image, screenX, screenY, gw.tileSize, gw.tileSize, null);
        }
    }
}
