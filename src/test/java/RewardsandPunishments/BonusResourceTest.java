package RewardsandPunishments;

import RewardsAndPunishments.BonusResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BonusResourceTest {

    private BonusResource b1;

    @BeforeEach
    public void setup() {
        b1 = new BonusResource();
    }

    @Test
    public void BonusResourceTest() {
        assertEquals("BonusReward", b1.name);
        assertNotNull(b1.image);
        assertEquals(25, b1.points);
        assertEquals(true, b1.collision);
    }
}
