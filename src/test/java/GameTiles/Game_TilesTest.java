package GameTiles;

import GameTiles.Game_Tiles;
import RewardsAndPunishments.RegularResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Game_TilesTest {
    private Game_Tiles gameT1;

    @BeforeEach
    void setUp()  {
        gameT1 = new Game_Tiles();
    }

    @Test
    public void BonusResourceTest()  {
        assertEquals(false, gameT1.collide);
    }
}
