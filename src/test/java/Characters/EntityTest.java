package Characters;
import Characters.Entity;
import main.GameWindow;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

public class EntityTest {
    private GameWindow gameWindow;
    private Entity entity;

    @BeforeEach
    public void setUp()
    {
        gameWindow = new GameWindow();
        entity = new Entity(gameWindow);
    }

    @Test
    public void EntityConstructorTest()
    {
        assertNotNull(gameWindow);
        assertNotNull(entity.worldX);
        assertNotNull(entity.worldY);
        assertNotNull(entity.speed);
    }

    @Test
    public void EntitySpritesTest()
    {
        assertEquals(0,entity.spriteCounter);
        assertEquals(1,entity.spriteNum);
    }

    @Test
    public void EntityCollisionTest()
    {

        assertNotNull(entity.solidAreaDefaultX);
        assertNotNull(entity.solidAreaDefaultY);

        assertsolidAreaEquals(0,0,48,48,entity.solidArea);
        assertEquals(false, entity.collisionOn);
    }
    private void assertsolidAreaEquals(int x, int y, int width, int height, Rectangle solidArea) {
    }


}
