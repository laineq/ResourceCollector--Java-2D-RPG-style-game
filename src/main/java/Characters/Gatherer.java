package Characters;

import main.GameWindow;
import main.Input;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class for the main character in the game. It inherits from the Entity class.
 */

public class Gatherer extends Entity{
    public Image image = null;
    public boolean notifOn = false;
    public int notif_timer = 0;
    Input keyI; //to store the key input

    //coordinates of the gatherer on the displayed screen
    public final int screenX;
    public final int screenY;

    //stores the number of rewards gathered and to be collected
    public int rewardsToBeCollected = 10;
    public int resourcesGathered = 0;

    /**
     * Constructor to set the gatherer as the main character.
     * @param gw - game window on which the gatherer will appear on the screen
     * @param keyI - stores the key input to set the movement of the gatherer
     */
    public Gatherer(GameWindow gw, Input keyI){
        super(gw); //call the constructor of class Entity to set gw and its game window

        this.keyI = keyI; //sets the key input as keyI for movement

        //calculate and assigns the coordinates on the screen
        screenX = gw.scWidth/2 - (gw.tileSize/2);
        screenY = gw.scHeight/2 - (gw.tileSize/2);

        //creates a block around the player to avoid collision with walls
        solidArea = new Rectangle(0, 0, 48, 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //function calls to set default values and image of the gatherer
        setDefaultValues();
        getGathererImage();
    }

    /**
     * Sets the default values of the member variables.
     */
    public void setDefaultValues(){
        //coordinates to ensure that the gatherer starts at the start tile
        worldX = gw.tileSize * 11;
        worldY = gw.tileSize * 11;
        speed = 8; //sets the speed to a constant value
        direction = "down"; //the starting direction of the player in down (facing the screen)
    }

    /**
     * Sets the image of the gatherer according to the provided resources.
     */
    public void getGathererImage()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        down1 = toolkit.getImage("src/main/resources/gatherer/gatherer01.png");
        down2 = toolkit.getImage("src/main/resources/gatherer/gatherer02.png");

        up1 = toolkit.getImage("src/main/resources/gatherer/gatherer04.png");
        up2 = toolkit.getImage("src/main/resources/gatherer/gatherer05.png");

        right1 = toolkit.getImage("src/main/resources/gatherer/gatherer06.png");
        right2 = toolkit.getImage("src/main/resources/gatherer/gatherer07.png");

        left1 = toolkit.getImage("src/main/resources/gatherer/gatherer08.png");
        left2 = toolkit.getImage("src/main/resources/gatherer/gatherer09.png");
    }

    /**
     * Sets the direction according to the key pressed and checks for collision, rewards and scavengers.
     * If there is no collision, updates the image according to the direction and changes the sprite.
     */
    public boolean update() {
        boolean moved = false;

        //updates the direction according to the key pressed
        if (keyI.wPress || keyI.sPress || keyI.aPress || keyI.dPress) { // Simplified if statement
            if (keyI.wPress) {
                direction = "up";
            } else if (keyI.sPress) {
                direction = "down";
            } else if (keyI.aPress) {
                direction = "left";
            } else { // If statement unwrapped since keyI.dPress is always true
                direction = "right";
            }
            moved=true; // Move after getting direction

            //checks for collision
            collisionOn = false;
            gw.cch.checkTile(this);

            //checks is the tile has a reward, bonus reward or a trap
            int objIndex = gw.cch.checkObject(this, true);
            pickUpObject(objIndex);

            //checks if there was a scavenger on the tile
            int enemyIndex = gw.cch.checkScavenger(this, true);
            interactEnemy(enemyIndex);

            //if there is no collision moves the character sprite
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            //changes the sprite for the direction to create an effect of animation
            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            if(notifOn)
            {
                notif_timer++;
                if(notif_timer > 90)
                {
                    notifOn = false;
                    notif_timer = 0;
                }
            }
        }
        return moved;
    }

    /**
     * Checks is the tile the gatherer is on has a reward, bonus reward or a trap.
     * or if the tile is the end tile and the player has won
     * or if the score is negative and the player has lost
     * @param i - the index of the object in the array of all objects: rewards, bonus rewards, traps, and end tile
     */
    public void pickUpObject(int i){
        //checks if the index is valid
        if(i != 999) {
            gw.score += gw.obj[i].points; //updates the score according to the points associated with the object

            //if the tile has a regular reward, updates the number of rewards to be collected and resources gathered
            if(gw.obj[i].name.equals("Reward")){ // Fixed string value comparisons with "equals()"
                notifOn = true;
                rewardsToBeCollected--;
                resourcesGathered = 10 - rewardsToBeCollected;
            }
            //checks if the player reaches the end tile
            else if(gw.obj[i].name.equals("EndTile"))
            {
                if(rewardsToBeCollected == 0) {
                    //checks if player has collected all regular rewards
                    gw.gameWon = true; //player wins
                }
            }
            //checks if the score is negative
            if(gw.score < 0) {
                gw.gameLost = true; //player loses
            }
            //object disappears when picked up, only if it is not the end tile
            if(!gw.obj[i].name.equals("EndTile")) {
                gw.obj[i] = null;
            }
        }
    }

    /**
     * Checks is the tile the gatherer is on has a scavenger on it
     * if yes the player loses
     * @param i - the index of the object in the array of all objects: rewards, bonus rewards, traps, and end tile
     */
    public void interactEnemy(int i){
        //checks if the index is valid
        if( i != 999){
            gw.gameLost = true; //player loses
        }
    }

    /**
     * Draws the sprite of the gatherer according to the direction
     * @param g2 - the Graphics2D class to draw the sprite of the gatherer
     */
    public void draw(Graphics2D g2) throws IOException, FontFormatException {
        image = null; //sets the image to null

        //checks the direction to update the gatherer sprite
        switch (direction) { // Fixed switch statement using enhanced switch statement
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        //draws the gatherer sprite on the game window
        g2.drawImage(image, screenX, screenY, gw.tileSize, gw.tileSize, null);
        if(notifOn) {
            g2.setColor(Color.white);
            Font displayFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")));
            g2.setFont(displayFont);
            //g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString("You have collected a resource", gw.tileSize / 2, gw.tileSize * 3);
        }
    }

    /**
     * get the x coordinate of gatherer
     * @return the current x coordinate of gatherer
     */
    public int getX() {
        return worldX;
    }

    /**
     * get the y coordinate of gatherer
     * @return the current y coordinate of gatherer
     */
    public int getY() {
        return worldY;
    }

}
