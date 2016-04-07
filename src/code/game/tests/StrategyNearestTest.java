package code.game.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.game.models.TowerCastle;
import code.game.models.TowerIndustrial;
import code.game.models.Critter;
import code.game.models.CritterFactory;
import code.game.models.GameMap;
import code.game.models.TowerModel;
import code.game.strategies.StrategyNearest;

/**
 * Test case class to test the nearest critter targeting strategy.
 * 
 * @author lokesh
 * @author Umer
 * @version 1.0.0
 */

public class StrategyNearestTest {

	/**
	 * Test to check if the nearest critter to tower is selected by the tower strategy.
	 */
	@Test
	public void testStrategy() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0, 0, 0},
			{ 0, 2, 0, 0, 0, 0},
			{ 0, 3, 4, 0, 0, 0},
			{ 0, 0, 5, 0, 0, 0},
			{ 0, 0, 6, 0, 0, 0},
			{ 0, 0, 9999, 0, 0, 0}}; 
		
		GameMap tstGameMapObj = new GameMap();
		tstGameMapObj.initialize("testmap", mapArray);
		TowerModel tstTwr = new TowerCastle(); 
		tstTwr.setMyLocationOnMap("12");
		tstTwr.upgradeCurrentLevel();
		tstGameMapObj.addTower("12", tstTwr);
		
		Critter tstCrtr1 = CritterFactory.getCritter(1, tstGameMapObj);
		tstCrtr1.setMyLocationOnMap("11");
		Critter tstCrtr2 = CritterFactory.getCritter(2, tstGameMapObj);
		tstCrtr2.setMyLocationOnMap("32");
		tstGameMapObj.addCritter("11", tstCrtr1);
		tstGameMapObj.addCritter("32", tstCrtr2);
		
		StrategyNearest tstStrtgy = new StrategyNearest();
		tstStrtgy.shootCritters(tstGameMapObj, tstTwr);
		
		assertTrue(tstStrtgy.lockedCritter.getMyLocationOnMap().equals("11"));
	}

	/**
	 * Test to check if the critter is hit by burning.
	 */
	@Test
	public void testBurnSpecialEffect() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0, 0, 0},
			{ 0, 2, 0, 0, 0, 0},
			{ 0, 3, 4, 0, 0, 0},
			{ 0, 0, 5, 0, 0, 0},
			{ 0, 0, 6, 0, 0, 0},
			{ 0, 0, 9999, 0, 0, 0}}; 
		
		GameMap tstGameMapObj = new GameMap();
		tstGameMapObj.initialize("testmap", mapArray);
		TowerModel tstTwr = new TowerCastle(); 
		tstTwr.setMyLocationOnMap("12");
		tstTwr.upgradeCurrentLevel();
		tstGameMapObj.addTower("12", tstTwr);
		
		Critter tstCrtr1 = CritterFactory.getCritter(1, tstGameMapObj);
		tstCrtr1.setMyLocationOnMap("11");
		tstGameMapObj.addCritter("11", tstCrtr1);
		
		StrategyNearest tstStrtgy = new StrategyNearest();
		tstStrtgy.shootCritters(tstGameMapObj, tstTwr);
		
		assertTrue(tstCrtr1.getBackground().equals("red") && tstCrtr1.getLastHitBy().equals("Castle Tower"));
	}
	
	/**
	 * Test to check if the critter is hit by splash effect.
	 */
	@Test
	public void testSplashSpecialEffect() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0, 0, 0},
			{ 0, 2, 0, 0, 0, 0},
			{ 0, 3, 4, 0, 0, 0},
			{ 0, 0, 5, 0, 0, 0},
			{ 0, 0, 6, 0, 0, 0},
			{ 0, 0, 9999, 0, 0, 0}}; 
		
		GameMap tstGameMapObj = new GameMap();
		tstGameMapObj.initialize("testmap", mapArray);
		TowerModel tstTwr = new TowerIndustrial(); 
		tstTwr.setMyLocationOnMap("12");
		tstGameMapObj.addTower("12", tstTwr);
		
		Critter tstCrtr1 = CritterFactory.getCritter(1, tstGameMapObj);
		tstCrtr1.setMyLocationOnMap("22");
		tstGameMapObj.addCritter("22", tstCrtr1);
		Critter tstCrtr2 = CritterFactory.getCritter(1, tstGameMapObj);
		tstCrtr2.setMyLocationOnMap("42");
		tstGameMapObj.addCritter("42", tstCrtr2);
		
		StrategyNearest tstStrtgy = new StrategyNearest();
		tstStrtgy.shootCritters(tstGameMapObj, tstTwr);
		
		assertTrue(tstCrtr2.getBackground().equals("black"));
	}
}
