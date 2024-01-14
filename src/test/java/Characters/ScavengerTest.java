package Characters;
import main.GameWindow;
import org.junit.jupiter.api.Test;

import Characters.Scavenger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScavengerTest {
	private GameWindow gameWindow;
	private Scavenger scavenger;
	
	@BeforeEach
	public void setUp() {
		gameWindow = new GameWindow();
		scavenger = new Scavenger(gameWindow);
	}
	
	@Test
	public void ScavengerConstructorTest() {
		assertNotNull(gameWindow);
		assertEquals("down", scavenger.direction);
		assertEquals(3, scavenger.speed);
	}
	
	@Test
	public void getEnemyImageTest() {
		assertNotNull(scavenger.up1);
		assertNotNull(scavenger.up2);
		assertNotNull(scavenger.down1);
		assertNotNull(scavenger.down2);
		assertNotNull(scavenger.left1);
		assertNotNull(scavenger.left2);
		assertNotNull(scavenger.right1);
		assertNotNull(scavenger.right2);
	}
	
	@Test
	public void setActionTest() {
		scavenger.setAction();
		assertEquals(1,scavenger.actionLockCounter);
		scavenger.actionLockCounter = 119;
		scavenger.setAction();
		assertEquals(0, scavenger.actionLockCounter);
	}
	
	@Test
	public void interactPlayerTest() {
		scavenger.interactPlayer(999);
		assertEquals(false, gameWindow.gameLost);
		scavenger.interactPlayer(2);
		assertEquals(true, gameWindow.gameLost);
	}
}
