package code.game.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.game.models.Critter;
import code.game.models.CritterFactory;
import code.game.models.GameMap;

/**
 * Test case class to test the factory design pattern of critters.
 * 
 * @author lokesh
 * @version 1.0.0
 */
public class CritterFactoryTest {

	/**
	 * Test to check if the factory design is implemented properly and polymorphism is achieved.
	 */
	@Test
	public void testCheckFactoryDesign() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, -5, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 
		
		GameMap tstGameMapObj = new GameMap();
		tstGameMapObj.initialize("testmap", mapArray);
		
		Critter tstCrtr1 = CritterFactory.getCritter(1, tstGameMapObj);
		Critter tstCrtr2 = CritterFactory.getCritter(2, tstGameMapObj);
		
		assertTrue(tstCrtr1.getClass() != tstCrtr2.getClass());
	}
	
	

}
