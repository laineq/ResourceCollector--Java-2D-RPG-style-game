package RewardsandPunishments;
import org.junit.jupiter.api.Test;

import RewardsAndPunishments.Trap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrapTest {
	private Trap trap;
	
	@BeforeEach
	public void setup() {
		trap = new Trap();
	}
	
	@Test
	public void TrapContructorTest() {
		assertEquals("Punishment", trap.name);
		assertNotNull(trap.image);
		assertEquals(-10, trap.points);
		assertEquals(true, trap.collision);
	}
}
