package main;

import Characters.Entity;
import Characters.Gatherer;
import Characters.Scavenger;
import GameTiles.Tile_Manager;
import RewardsAndPunishments.RegularResource;
import RewardsAndPunishments.Trap;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollisionCheckerTest {
    private GameWindow gw;
    private Entity e;

    @BeforeEach
    public void setUp(){
        gw = new GameWindow();
        e = new Gatherer(gw,gw.keyI);
    }

    @Test
    public void testWall()
    {
        Tile_Manager tiles = new Tile_Manager(gw);
        tiles.mapTileNum[0][0] = 0;

        e.worldX = 0;
        e.worldY = 0;

        gw.cch.checkTile(e);

        Assert.assertEquals(true, e.collisionOn);
    }

    @Test
    public void testWalkableArea()
    {
        gw.cch.checkTile(e);

        Assert.assertEquals(false, e.collisionOn);
    }

    @Test
    public void testReward()
    {
        gw.obj[0] = new RegularResource();
        gw.obj[0].worldX = 9;
        gw.obj[0].worldY = 9;

        e.worldX = gw.obj[0].worldX;
        e.worldY = gw.obj[0].worldY;

        Assert.assertEquals(0,gw.cch.checkObject(e,true));

        Assert.assertEquals(true, e.collisionOn);
    }

    @Test
    public void testTrap()
    {
        gw.obj[0] = new Trap();
        gw.obj[0].worldX = 8;
        gw.obj[0].worldY = 8;

        e.worldX = gw.obj[0].worldX;
        e.worldY = gw.obj[0].worldY;

        Assert.assertEquals(0,gw.cch.checkObject(e,true));

        Assert.assertEquals(true, e.collisionOn);
    }

    @Test
    public void testEnemy()
    {
        gw.scavengers[0] = new Scavenger(gw);
        gw.scavengers[0].worldX = 10;
        gw.scavengers[0].worldY = 10;
        gw.scavengers[0].collisionOn = true;

        e.worldX = gw.scavengers[0].worldX;
        e.worldY = gw.scavengers[0].worldY;

        Assert.assertEquals(0,gw.cch.checkScavenger(e,true));

        Assert.assertEquals(true, e.collisionOn);
    }

    @Test
    public void testPlayer()
    {
        gw.scavengers[0] = new Scavenger(gw);
        gw.scavengers[0].collisionOn = true;
        gw.scavengers[0].worldX = 7;
        gw.scavengers[0].worldY = 7;

        e.worldX = gw.scavengers[0].worldX;
        e.worldY = gw.scavengers[0].worldY;
        e.collisionOn = true;

        Assert.assertEquals(true, gw.scavengers[0].collisionOn);
    }

    @AfterEach
    public void tearDown(){
        gw = null;
        e = null;
    }
}
