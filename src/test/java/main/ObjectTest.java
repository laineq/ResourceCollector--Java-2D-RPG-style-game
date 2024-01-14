package main;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObjectTest {
    private Object o;

    @BeforeEach
    public void setUp(){
        o = new Object();
    }

    @Test
    public void checkCollision(){
        Assert.assertFalse(o.collision);
    }

    @Test
    public void nullMemberVariables(){
        Assert.assertNull(o.image);
        Assert.assertNull(o.name);
    }

    @Test
    public void ZeroMemberVariables(){
        Assert.assertEquals(0, o.worldX);
        Assert.assertEquals(0, o.worldY);
        Assert.assertEquals(0, o.points);
    }

    @Test
    public void testSolidRectangle(){
        Assert.assertNotNull(o.solidArea);
        Assert.assertEquals(0, o.solidAreaDefaultX);
        Assert.assertEquals(0, o.solidAreaDefaultY);
    }

    @AfterEach
    public void tearDown(){
        o = null;
    }
}
