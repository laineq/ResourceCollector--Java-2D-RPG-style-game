package main;
import main.GameWindow;
import org.junit.jupiter.api.Test;

import Characters.Gatherer;
import main.Input;
import main.UI;
import Characters.Scavenger;
import GameTiles.Tile_Manager;
import GameTiles.Game_Tiles;
import SetGame.AssetSetter;
import SetGame.gameStat;
import SetGame.gameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;

public class GameWindowTest {

    private GameWindow gameWindow;
    private Input input;
    private UI ui;
    private Gatherer gatherer;
    private Scavenger scavenger;
    private Game_Tiles game_tiles;
    private Tile_Manager tile_manager;
    private AssetSetter assetSetter;
    private gameStat gameStat;
    private gameState gameState;

    @BeforeEach
    public void setUp()
    {
        gameWindow = new GameWindow();
        input = new Input(gameWindow);
        ui = new UI(gameWindow);
        gatherer = new Gatherer(gameWindow,input);
        scavenger = new Scavenger(gameWindow);
        game_tiles = new Game_Tiles();
        tile_manager = new Tile_Manager(gameWindow);
        assetSetter = new AssetSetter(gameWindow);
        gameStat = new gameStat(gameWindow);
        gameState = new gameState(gameWindow,input);
    }

    @Test
    public void GameWindowConstructorTest()
    {
        assertNotNull(gameWindow);

        assertEquals(32, gameWindow.ogTileSize);
        assertEquals(2, gameWindow.scale);

        assertEquals(20,gameWindow.maxScCol);
        assertEquals(15,gameWindow.maxScRow);
        assertNotNull(gameWindow.scWidth);
        assertNotNull(gameWindow.scHeight);

        assertEquals(70,gameWindow.maxWorldCol);
        assertEquals(70,gameWindow.maxWorldRow);

        assertEquals(30, gameWindow.FPS);
        assertEquals(0,gameWindow.score);

        assertNotNull(gameWindow.keyI);
        assertNotNull(gameWindow.ui);

        assertNotNull(gameWindow.stats);
        assertNotNull(gameWindow.gatherer);
        assertNotNull(gameWindow.obj);
        assertNotNull(gameWindow.scavengers);

        assertEquals(false,gameWindow.gameLost);
        assertEquals(false,gameWindow.gameWon);
        assertEquals(false,gameWindow.gamePaused);
        assertEquals(false,gameWindow.gameResumed);
        assertEquals(false,gameWindow.gameReset);
    }

}
