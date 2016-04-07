package code.game.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.junit.Assert;
import org.junit.Test;

import code.game.controllers.SingleGameController;
import code.game.models.Critter;
import code.game.models.CritterBasic;
import code.game.models.CritterFactory;
import code.game.models.TowerCastle;
import code.game.models.TowerModel;
import code.game.strategies.StrategyNearest;
import code.game.models.GameMap;
import code.game.models.MapModel;
import code.game.utils.Util;

/**
 * Test case class to perform unit test on GameMap model class.
 * 
 * @author lokesh
 * @author Iftikhar
 * @version 1.0.0
 */
public class GameMapTest {

	/**
	 * Test to check if the map model object is created correctly from the map in file.
	 */
	@Test
	public void testLoadMap() {
		int[][] tstMapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}};

			GameMap tstGameMapObj = new GameMap();
			try{
				File selectedFile = new File(System.getProperty("user.dir") + "/maps/testmap.map");
				FileInputStream fis = new FileInputStream(selectedFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				MapModel mapModel = (MapModel) ois.readObject();
				int[][] mapArray = mapModel.getMapArray();
				tstGameMapObj.initialize("testMap2", mapArray);
			}
			catch (Exception ex) {}

			Assert.assertArrayEquals(tstMapArray, tstGameMapObj.getMapArray());  // (tstGameMapObj.CheckTowerExists("12"));
	}

	/**
	 * Test to check if a tower can be successfully added to the scenery.
	 */
	@Test
	public void testAddTower() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			GameMap tstGameMapObj = new GameMap();
			tstGameMapObj.initialize("testmap", mapArray);
			tstGameMapObj.addTower("12", new TowerCastle());
			int[][] newTstArray =  new int[][]{ { 0, 1, 0, 0 },
				{ 0, 2, -7, 0},
				{ 0, 3, 4, 0},
				{ 0, 0, 9999, 0}}; 

				Assert.assertArrayEquals(newTstArray, tstGameMapObj.getMapArray());  // (tstGameMapObj.CheckTowerExists("12"));
	}

	/**
	 * Test to check if a tower can be successfully deleted from the map.
	 */

	@Test
	public void testDeleteTower() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, -7, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			GameMap tstGameMapObj = new GameMap();
			tstGameMapObj.initialize("testmap", mapArray);
			tstGameMapObj.getTowerCollection().put("12", new TowerCastle());
			tstGameMapObj.deleteTowerFromMap("12");
			int[][] newTstArray =  new int[][]{ { 0, 1, 0, 0 },
				{ 0, 2, 0, 0},
				{ 0, 3, 4, 0},
				{ 0, 0, 9999, 0}}; 

				Assert.assertArrayEquals(newTstArray, tstGameMapObj.getMapArray());  // (tstGameMapObj.CheckTowerExists("12"));
	}

	/**
	 * Test to check if critter exist at a particular location in map.
	 */
	@Test
	public void testCheckCritrExist() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, -7, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			GameMap tstGameMapObj = new GameMap();
			tstGameMapObj.initialize("testmap", mapArray);
			tstGameMapObj.addCritter("11", new CritterBasic(tstGameMapObj));
			tstGameMapObj.getCritterFromCollection("11").setMyLocationOnMap("11");
			assertTrue(tstGameMapObj.checkCritterExists("11"));
	}

	/**
	 * Check if all the towers in the map shoots some critter in its visibility based on its strategy.
	 * (Integration test)
	 */
	@Test
	public void testTowrShoot() {
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0, 0, 0},
			{ 0, 2, 0, 0, 0, 0},
			{ 0, 3, 4, 0, 0, 0},
			{ 0, 0, 5, 0, 0, 0},
			{ 0, 0, 6, 0, 0, 0},
			{ 0, 0, 9999, 0, 0, 0}}; 

			GameMap tstGameMapObj = new GameMap();
			tstGameMapObj.initialize("testmap", mapArray);
			TowerModel tstTwr1 = new TowerCastle(); 
			tstTwr1.setMyLocationOnMap("12");
			tstTwr1.setStrategy(new StrategyNearest(), tstGameMapObj);
			tstGameMapObj.addTower("12", tstTwr1);
			tstTwr1.upgradeCurrentLevel();
			TowerModel tstTwr2 = new TowerCastle(); 
			tstTwr2.setStrategy(new StrategyNearest(), tstGameMapObj);
			tstTwr2.setMyLocationOnMap("41");
			tstGameMapObj.addTower("41", tstTwr2);

			Critter tstCrtr1 = CritterFactory.getCritter(1, tstGameMapObj);
			tstCrtr1.setMyLocationOnMap("11");
			Critter tstCrtr2 = CritterFactory.getCritter(2, tstGameMapObj);
			tstCrtr2.setMyLocationOnMap("32");
			Critter tstCrtr3 = CritterFactory.getCritter(2, tstGameMapObj);
			tstCrtr3.setMyLocationOnMap("42");
			tstGameMapObj.addCritter("11", tstCrtr1);
			tstGameMapObj.addCritter("32", tstCrtr2);
			tstGameMapObj.addCritter("42", tstCrtr3);

			assertTrue(tstGameMapObj.towerToShoot() == 2);
	}
}
