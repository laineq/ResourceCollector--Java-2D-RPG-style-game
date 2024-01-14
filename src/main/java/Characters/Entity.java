package Characters;

import main.GameWindow;

import java.awt.*;

/**
 * Class for all entities in the game. It is the super class for Gatherer and Scavenger classes
 */

public class Entity {
    GameWindow gw; //game window for the game

    public int worldX, worldY; //coordinates according to the world map
    public int speed; //the speed of the entity

    public Image up1, up2, down1, down2, left1, left2, right1, right2; //to store the sprites of the entity
    public String direction; //the direction the entity is facing or going towards

    //variables to change the sprite when moving
    public int spriteCounter = 0;
    public int spriteNum = 1;

    //rectangle around the character to detect collision and block movement accordingly
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false; //set default collision to false

    /**
     * Constructor to set the game window member variable.
     * @param gw - game window to display the maze game on the screen
     */
    public  Entity(GameWindow gw){
        this.gw = gw;
    }
}
