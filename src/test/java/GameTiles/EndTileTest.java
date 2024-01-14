package GameTiles;

import org.junit.jupiter.api.Test;

import GameTiles.EndTile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EndTileTest {
	private EndTile ET;
	
	@BeforeEach
	public void setUp() {
		ET = new EndTile();
	}
	
	@Test
	public void EndTileContructorTest() {
		assertEquals("EndTile", ET.name);
		assertNotNull(ET.image);
		assertEquals(0, ET.points);
		assertEquals(false, ET.collision);
	}
}
