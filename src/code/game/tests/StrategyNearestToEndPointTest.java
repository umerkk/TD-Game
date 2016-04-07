package code.game.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.game.models.Critter;
import code.game.models.CritterFactory;
import code.game.models.GameMap;
import code.game.models.TowerCastle;
import code.game.models.TowerModel;
import code.game.strategies.StrategyNearest;
import code.game.strategies.StrategyNearestToEndPoint;

public class StrategyNearestToEndPointTest {

	/**
	 * Test to check if the nearest critter to tower is selected by the tower strategy.
	 */
	@Test
	public void testStrategy() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0, 0, 0},
			{ 0, 2, -5, 0, 0, 0},
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
		
		StrategyNearestToEndPoint tstStrtgy = new StrategyNearestToEndPoint();
		tstStrtgy.shootCritters(tstGameMapObj, tstTwr);
		
		assertTrue(tstStrtgy.lockedCritter.getMyLocationOnMap().equals("32"));
	}

}
