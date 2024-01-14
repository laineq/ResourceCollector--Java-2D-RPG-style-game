package main;

import Characters.Entity;

/**
 * Class for collision between the entities, objects and tiles on the maze
 */
public class CollisionChecker {

    GameWindow gw;

    /**
     * Sets the game window for which it checks for collision
     * @param gw - game window on which the object might collide
     */
    public CollisionChecker(GameWindow gw) {
        this.gw = gw;
    }

    /**
     * Checks the tile on which the entity moves to
     * @param collideArea - boolean value for collision of tileNum1 and tileNum2
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     */
    public void setCollision(boolean collideArea, Entity e) {
        if(collideArea) {
            e.collisionOn = true;
        }
    }

    /**
     * Checks whether the gatherer is colliding with an object
     * @param e - Entity (gatherer or scavenger) that moves on the object to be checked
     */
    public void areaAndSpeed(Entity e) {
        switch (e.direction) {
            case "up":
                e.solidArea.y -= e.speed;
                break;
            case "down":
                e.solidArea.y += e.speed;
                break;
            case "left":
                e.solidArea.x -= e.speed;
                break;
            case "right":
                e.solidArea.x += e.speed;
                break;
        }
    }

    /**
     * Checks the tile on which the entity moves to
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     */
    public void checkTile(Entity e)
    {
        //assigns the variables to check for collision
        int entityLeftWorldX = e.worldX + e.solidArea.x;
        int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
        int entityTopWorldY = e.worldY + e.solidArea.y;
        int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;

        //assigns the variables for the entity's position
        int entityLeftCol = entityLeftWorldX/gw.tileSize;
        int entityRightCol = entityRightWorldX/gw.tileSize;
        int entityTopRow = entityTopWorldY/gw.tileSize;
        int entityBottomRow = entityBottomWorldY/gw.tileSize;

        //checks for collision according to the direction the entity is moving towards
        int tileNum1 = 0, tileNum2 = 0;
        boolean collideTiles;
        switch(e.direction)
        {
            case "up":
                entityTopRow = (entityTopWorldY - e.speed)/gw.tileSize;
                tileNum1 = gw.background.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gw.background.mapTileNum[entityRightCol][entityTopRow];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + e.speed)/gw.tileSize;
                tileNum1 = gw.background.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gw.background.mapTileNum[entityRightCol][entityBottomRow];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - e.speed)/gw.tileSize;
                tileNum1 = gw.background.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gw.background.mapTileNum[entityLeftCol][entityBottomRow];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + e.speed)/gw.tileSize;
                tileNum1 = gw.background.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gw.background.mapTileNum[entityRightCol][entityBottomRow];
                break;
        }
        collideTiles = (gw.background.gameTiles[tileNum1].collide || gw.background.gameTiles[tileNum2].collide);
        setCollision(collideTiles, e);
    }

    /**
     * Checks whether the gatherer is colliding with an object
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     * @param player - gatherer which collides with the object
     */
    public int checkObject(Entity e, boolean player) {
        int index = 999; //sets the index to a temporary value

        //checks for collision according to the direction the entity is moving towards
        for (int i = 0; i < gw.obj.length; i++) {
            if (gw.obj[i] != null) {
                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;

                gw.obj[i].solidArea.x = gw.obj[i].worldX + gw.obj[i].solidArea.x;
                gw.obj[i].solidArea.y = gw.obj[i].worldY + gw.obj[i].solidArea.y;

                areaAndSpeed(e);
                boolean collideObj = (e.solidArea.intersects(gw.obj[i].solidArea) && gw.obj[i].collision == true);
                setCollision(collideObj, e);
                if (e.solidArea.intersects(gw.obj[i].solidArea) && player == true) {
                    index = i;
                }

                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;

                gw.obj[i].solidArea.x = gw.obj[i].solidAreaDefaultX;
                gw.obj[i].solidArea.y = gw.obj[i].solidAreaDefaultY;
            }
        }
        return index; //returns the true index
    }

    /**
     * Checks whether the gathere is colliding with a scavenger
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     * @param player - gatherer which collides with the object
     */
    public int checkScavenger(Entity e, boolean player) {
        int index = 999;

        //checks for collision according to the direction the entity is moving towards
        for (int i = 0; i < gw.scavengers.length; i++) {
            if (gw.scavengers[i] != null) {
                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;

                gw.scavengers[i].solidArea.x = gw.scavengers[i].worldX + gw.scavengers[i].solidArea.x;
                gw.scavengers[i].solidArea.y = gw.scavengers[i].worldY + gw.scavengers[i].solidArea.y;

                areaAndSpeed(e);
                boolean collideScavenger = (e.solidArea.intersects(gw.scavengers[i].solidArea) && gw.scavengers[i].collisionOn == true);
                setCollision(collideScavenger, e);
                if (e.solidArea.intersects(gw.scavengers[i].solidArea) && player == true) {
                    index = i;
                }

                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;

                gw.scavengers[i].solidArea.x = gw.scavengers[i].solidAreaDefaultX;
                gw.scavengers[i].solidArea.y = gw.scavengers[i].solidAreaDefaultY;
            }
        }
        return index; //returns the index value
    }

    /**
     * Checks whether the scavenger and the gatherer are colliding
     * @param e - Entity (gatherer or scavenger) that moves on the tile to be checked
     * @param enemy - gatherer which collides with the object
     */
    public int checkGatherer(Entity e, boolean enemy) {
        int index = 999;

        //if there is a gatherer on the maze checks for collision
        if (gw.gatherer != null) {
            e.solidArea.x = e.worldX + e.solidArea.x;
            e.solidArea.y = e.worldY + e.solidArea.y;

            gw.gatherer.solidArea.x = gw.gatherer.worldX + gw.gatherer.solidArea.x;
            gw.gatherer.solidArea.y = gw.gatherer.worldY + gw.gatherer.solidArea.y;

            //checks for collision in the particular direction
            areaAndSpeed(e);
            boolean collideGatherer = (e.solidArea.intersects(gw.gatherer.solidArea) && gw.gatherer.collisionOn == true);
            setCollision(collideGatherer, e);
            if (e.solidArea.intersects(gw.gatherer.solidArea) && enemy == true) {
                index = 0;
            }

            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;

            gw.gatherer.solidArea.x = gw.gatherer.solidAreaDefaultX;
            gw.gatherer.solidArea.y = gw.gatherer.solidAreaDefaultY;
        }
        return index; //returns the index value
    }
}
