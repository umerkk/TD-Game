package code.game.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.game.models.TowerCastle;
import code.game.models.Critter;
import code.game.models.CritterFactory;
import code.game.models.GameMap;
import code.game.models.TowerModel;
import code.game.strategies.StrategyWeakest;

/**
 * Test case class to test the weakest critter targeting strategy.
 * 
 * @author lokesh
 * @author Armaghan
 * @version 1.0.0 
 */
public class StrategyWeakestTest {

	/**
	 * Test to check if the weakest critter is selected by the tower strategy.
	 */
	@Test
	public void testStrategy() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, -5, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			GameMap tstGameMapObj = new GameMap();
			tstGameMapObj.initialize("testmap", mapArray);
			TowerModel tstTwr = new TowerCastle(); 
			tstTwr.setMyLocationOnMap("12");
			tstGameMapObj.addTower("12", tstTwr);

			Critter tstCrtr1 = CritterFactory.getCritter(1, tstGameMapObj);
			tstCrtr1.setMyLocationOnMap("11");
			Critter tstCrtr2 = CritterFactory.getCritter(2, tstGameMapObj);
			tstCrtr2.setMyLocationOnMap("22");
			tstGameMapObj.addCritter("11", tstCrtr1);
			tstGameMapObj.addCritter("22", tstCrtr2);

			StrategyWeakest tstStrtgy = new StrategyWeakest();
			tstStrtgy.shootCritters(tstGameMapObj, tstTwr);

			assertTrue(tstStrtgy.weakestCritter.getMyLocationOnMap().equals("11"));
	}

}
