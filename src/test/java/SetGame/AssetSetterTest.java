package SetGame;

import Characters.Scavenger;
import main.GameWindow;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssetSetterTest {
    private GameWindow gw;

    @BeforeEach
    public void setUp(){
        gw = new GameWindow();
        gw.aSetter.setObject();
        gw.aSetter.setEnemy();
    }

    @Test
    public void ObjectArrayNotNull()
    {
        for (Object o : gw.obj) {
            Assert.assertNotNull(o);
        }
    }

    @Test
    public void checkRegularResource(){
        Assert.assertEquals(896,gw.obj[0].worldX);
        Assert.assertEquals(1216,gw.obj[0].worldY);
    }

    @Test
    public void checkTrap(){
        Assert.assertEquals(2752,gw.obj[6].worldX);
        Assert.assertEquals(3264,gw.obj[6].worldY);
    }

    @Test
    public void ScavengerArrayNotNull()
    {
        for (Scavenger s : gw.scavengers) {
            Assert.assertNotNull(s);
        }
    }

    @Test
    public void checkScavenger(){
        Assert.assertEquals(2560,gw.scavengers[1].worldX);
        Assert.assertEquals(2432,gw.scavengers[1].worldY);
    }

    @AfterEach
    public void tearDown(){
        gw = null;
    }
}