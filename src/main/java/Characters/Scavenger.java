package Characters;

import main.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.Random;

import javax.imageio.ImageIO;
/**
 * A class of a movable entity that contains an object of Coordinates class and implements from the
 * Entity class
 */
public class Scavenger extends Entity{
    public int actionLockCounter = 0;

    /**
     * Creates a Scavenger object that can be shown on screen. Direction is set to 'right' by default
     *
     * @param gw - the game window
     * @throws IOException
     */
    public Scavenger(GameWindow gw) {
        super(gw);

        direction = "down";
        speed = 3;

        getEnemyImage();
    }
    
    /**
     * Sets the image members with the Scavenger resources.
     */
    public void getEnemyImage() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        up1 = toolkit.getImage("src/main/resources/scavenger/scavenger_up_1.png");
        up2 = toolkit.getImage("src/main/resources/scavenger/scavenger_up_2.png");

        down1 = toolkit.getImage("src/main/resources/scavenger/scavenger_down_1.png");
        down2 = toolkit.getImage("src/main/resources/scavenger/scavenger_down_2.png");

        left1 = toolkit.getImage("src/main/resources/scavenger/scavenger_left_1.png");
        left2 = toolkit.getImage("src/main/resources/scavenger/scavenger_left_2.png");

        right1 = toolkit.getImage("src/main/resources/scavenger/scavenger_right_1.png");
        right2 = toolkit.getImage("src/main/resources/scavenger/scavenger_right_2.png");
    }


    /**
    * Sets the direction for scavenger movement
    */
    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else if (i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    /**
     * Paints and displays the Scavenger to the screen depending on its direction.
     */
    public void draw(Graphics2D g2) {

        Image image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        
        drawCurrentScreen(g2, image);
        
    }
    
    /**
     * Checks whether the Scavenger has collided with the Gatherer and animates the Scavenger.
     * @param gathererX : the gatherer's x coordinate
     * @param gathererY : the gatherer's y coordinate
     */
    public void update(int gathererX, int gathererY){
        int disX = Math.abs(worldX-gathererX);
        int disY = Math.abs(worldY-gathererY);

        trackPlayer(disX, disY, gathererX, gathererY);
        
        setAction();
        this.collisionOn = false;
        gw.cch.checkTile(this);
        int checkCollision = gw.cch.checkGatherer(this, true);
        interactPlayer(checkCollision);
        //if not collision with wall, change the Scavenger coordinate
        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }

        }

        animate();
    }

    /**
     * Ends the game if any other number is passed into this method.
     * 
     *  @param i - an integer
     */
    public void interactPlayer(int i) {
        if(i != 999){
            gw.gameLost = true;
        }
    }
    
    /**
     * Draws the Scavenger to screen if it is within the viewing screen the Gatherer is currently showing.
     * 
     * @param g - Graphics2D object
     * @param i - Image object
     * @param x - x-coordinate of the current shown screen
     * @param y - y-coordinate of the current shown screen
     */
    private void drawCurrentScreen(Graphics2D g, Image i) {
    	int screenX = worldX - gw.gatherer.worldX + gw.gatherer.screenX;
        int screenY = worldY - gw.gatherer.worldY + gw.gatherer.screenY;
    	if (worldX + gw.tileSize > gw.gatherer.worldX - gw.gatherer.screenX &&
                worldX - gw.tileSize < gw.gatherer.worldX + gw.gatherer.screenX &&
                worldY + gw.tileSize > gw.gatherer.worldY - gw.gatherer.screenY &&
                worldY - gw.tileSize < gw.gatherer.worldY + gw.gatherer.screenY) {
            g.drawImage(i, screenX, screenY, gw.tileSize, gw.tileSize, null);
        }
    }

    /**
     * Scavenger moves towards a direction that makes them closest to the current position of the Gatherer.
     * @param x - the distance, in the x direction, from the Gatherer
     * @param y - the distance, in the y direction, from the Gatherer
     * @param gX - the x-coordinate of the Gatherer
     * @param gY - the y-coordinate of the Gatherer
     */
    private void trackPlayer(int x, int y, int gX, int gY) {
    	if(x>=y) {
            if (gX < worldX) {
                direction = "left";
            } else if (gX > worldX) {
                direction = "right";
            }
        }
        else {
            if (gY < worldY) {
                direction = "up";
            } else if (gY > worldY) {
                direction = "down";
            }
        }

    }
    
    /**
     * Animates the Scavenger
     */
    private void animate() {
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }


}
