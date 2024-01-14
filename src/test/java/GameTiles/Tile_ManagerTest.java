package GameTiles;

import GameTiles.Game_Tiles;
import GameTiles.Tile_Manager;
import main.GameWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Tile_ManagerTest {
    private GameWindow gw;
    private Game_Tiles[] gT1;
    private int[][] mapN;

    @BeforeEach
    void setUp() {
        gw = new GameWindow();
        gT1 = new Game_Tiles[10];
        mapN = new int[gw.maxWorldCol][gw.maxWorldRow];

    }

    @Test
    void Tile_ManagerConstructorTest() {
        assertNotNull(gw);
        assertNotNull(gT1);
    }


    @Test
    void loadMap() {
        assertNotNull(mapN);
    }


}
