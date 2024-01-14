package Characters;
import Characters.Scavenger;
import main.GameWindow;
import org.junit.jupiter.api.Test;

import Characters.Gatherer;
import main.Input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

public class GathererTest {
    private GameWindow gameWindow;
    private Input input;
    private Gatherer gatherer;

    @BeforeEach
    public void setUp() {
        gameWindow = new GameWindow();
        input = new Input(gameWindow);
        gatherer = new Gatherer(gameWindow, input);
    }

    @Test
    public void GathererConstructorTest() {
        assertNotNull(gameWindow);

        assertNotNull(gatherer.screenX);
        assertNotNull(gatherer.screenY);
    }

    @Test
    public void getGathererImageTest() {
        assertNotNull(gatherer.up1);
        assertNotNull(gatherer.up2);
        assertNotNull(gatherer.down1);
        assertNotNull(gatherer.down2);
        assertNotNull(gatherer.left1);
        assertNotNull(gatherer.left2);
        assertNotNull(gatherer.right1);
        assertNotNull(gatherer.right2);
    }

    @Test
    public void GathererResourcesTest() {
        assertEquals(10, gatherer.rewardsToBeCollected);
        assertEquals(0, gatherer.resourcesGathered);
    }

    @Test
    public void GathererCollisionTest() {

        assertNotNull(gatherer.solidAreaDefaultX);
        assertNotNull(gatherer.solidAreaDefaultY);

        assertsolidAreaEquals(0,0,48,48,gatherer.solidArea);
        assertEquals(false, gatherer.collisionOn);

    }
    private void assertsolidAreaEquals(int x, int y, int width, int height, Rectangle solidArea) {
    }

    @Test
    public void GathererDefaultValuesTest() {
        assertNotNull(gatherer.worldX);
        assertNotNull(gatherer.worldY);

        assertEquals(8,gatherer.speed);
        assertEquals("down", gatherer.direction);
    }

}
