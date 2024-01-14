package SetGame;

import Characters.Scavenger;
import GameTiles.EndTile;
import RewardsAndPunishments.BonusResource;
import RewardsAndPunishments.RegularResource;
import RewardsAndPunishments.Trap;
import main.GameWindow;

import java.awt.*;

/**
 * Class for the setting objects on the maze: rewards, bonus rewards, traps and end tile
 */
public class AssetSetter {
    GameWindow gw;

    /**
    * Constructor to set the game window for the assets.
    * @param gw - game window on which the assets will appear on the screen
    */
    public AssetSetter(GameWindow gw){
        this.gw = gw;
    }

    /**
     * Sets the positions of the rewards, bonus rewards, traps and end tile on the maze
     */
    public void setObject() {
        //sets the objects to a particular object and sets its coordinates
        gw.obj[0] = new RegularResource();
        gw.obj[0].worldX = 14 * gw.tileSize;
        gw.obj[0].worldY = 19 * gw.tileSize;

        gw.obj[1] = new RegularResource();
        gw.obj[1].worldX = 19 * gw.tileSize;
        gw.obj[1].worldY = 42 * gw.tileSize;

        gw.obj[2] = new RegularResource();
        gw.obj[2].worldX = 37 * gw.tileSize;
        gw.obj[2].worldY = 21 * gw.tileSize;

        gw.obj[3] = new RegularResource();
        gw.obj[3].worldX = 30 * gw.tileSize;
        gw.obj[3].worldY = 35 * gw.tileSize;

        gw.obj[4] = new BonusResource();
        gw.obj[4].worldX = 35 * gw.tileSize;
        gw.obj[4].worldY = 57 * gw.tileSize;

        gw.obj[5] = new Trap();
        gw.obj[5].worldX = 14 * gw.tileSize;
        gw.obj[5].worldY = 36 * gw.tileSize;

        gw.obj[6] = new Trap();
        gw.obj[6].worldX = 43 * gw.tileSize;
        gw.obj[6].worldY = 51 * gw.tileSize;

        gw.obj[7] = new Trap();
        gw.obj[7].worldX = 58 * gw.tileSize;
        gw.obj[7].worldY = 37 * gw.tileSize;

        gw.obj[8] = new RegularResource();
        gw.obj[8].worldX = 52 * gw.tileSize;
        gw.obj[8].worldY = 56 * gw.tileSize;

        gw.obj[9] = new RegularResource();
        gw.obj[9].worldX = 45 * gw.tileSize;
        gw.obj[9].worldY = 40 * gw.tileSize;

        gw.obj[10] = new RegularResource();
        gw.obj[10].worldX = 17 * gw.tileSize;
        gw.obj[10].worldY = 31 * gw.tileSize;

        gw.obj[11] = new RegularResource();
        gw.obj[11].worldX = 51 * gw.tileSize;
        gw.obj[11].worldY = 36 * gw.tileSize;

        gw.obj[12] = new RegularResource();
        gw.obj[12].worldX = 48 * gw.tileSize;
        gw.obj[12].worldY = 48 * gw.tileSize;

        gw.obj[13] = new RegularResource();
        gw.obj[13].worldX = 56 * gw.tileSize;
        gw.obj[13].worldY = 14 * gw.tileSize;

        gw.obj[14] = new BonusResource();
        gw.obj[14].worldX = 39 * gw.tileSize;
        gw.obj[14].worldY = 27 * gw.tileSize;

        //sets the end tile at the corner of the maze for the gatherer to reach and win
        gw.obj[15] = new EndTile();
        gw.obj[15].worldX = 58 * gw.tileSize;
        gw.obj[15].worldY = 58 * gw.tileSize;
    }

    /**
     * Sets the positions of the scavengers at the start of the game
     */
    public void setEnemy(){
        //sets the objects to an instance of the scavenger class and sets its coordinates
        gw.scavengers[0] = new Scavenger(gw);
        gw.scavengers[0].worldX = 14 * gw.tileSize;
        gw.scavengers[0].worldY = 56 * gw.tileSize;

        gw.scavengers[1] = new Scavenger(gw);
        gw.scavengers[1].worldX = 40 * gw.tileSize;
        gw.scavengers[1].worldY = 38 * gw.tileSize;

        gw.scavengers[2] = new Scavenger(gw);
        gw.scavengers[2].worldX = 50 * gw.tileSize;
        gw.scavengers[2].worldY = 15 * gw.tileSize;

        gw.scavengers[3] = new Scavenger(gw);
        gw.scavengers[3].worldX = 23 * gw.tileSize;
        gw.scavengers[3].worldY = 29 * gw.tileSize;

        gw.scavengers[4] = new Scavenger(gw);
        gw.scavengers[4].worldX = 50 * gw.tileSize;
        gw.scavengers[4].worldY = 41 * gw.tileSize;


    }

}
