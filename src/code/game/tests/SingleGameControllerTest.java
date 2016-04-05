package code.game.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Assert;
import org.junit.Test;

import code.game.controllers.SingleGameController;
import code.game.models.TowerCastle;
import code.game.models.GameData;
import code.game.models.GameMap;
import code.game.utils.Util;

/**
 * Test case class to perform tests on the SingleGameController class.
 * 
 * @author Iftikhar
 * @author lokesh
 * @version 1.0.0
 *
 */
public class SingleGameControllerTest {

	/**
	 * Test to read a map from file and check if it is read properly.
	 */
	@Test
	public void testReadMap() {
		int[][] tstMapArray =  new int[][]
				{{1, 0, 0, 0}, 
			{2, 3, 4, 0}, 
			{0, 0, 5, 0}, 
			{0, 0, 6, 9999}}; 

			SingleGameController testController = SingleGameController.getGameControllerInstance();
			testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));

			Assert.assertArrayEquals(tstMapArray, testController.getMapModel().getMapArray());
	}

	/**
	 * Test to check if SingleGameController is implementing Singleton design pattern.
	 */
	@Test
	public void testcheckSingltn() {
		SingleGameController tstCntrlr1 = SingleGameController.getGameControllerInstance();
		SingleGameController tstCntrlr2 = SingleGameController.getGameControllerInstance();

		assertTrue(tstCntrlr1.toString().equals(tstCntrlr2.toString()));
	}

	/**
	 * Test to sell a tower and get the account balance updated with refund value.
	 */
	@Test
	public void testSellTower() {
		GameData tstGmDat = new GameData();
		tstGmDat.setAccountBalance(100);

		GameMap tstMap = new GameMap();
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			tstMap.initialize("testmap", mapArray);
			tstMap.addTower("12", new TowerCastle());

			JPanel cell = new JPanel();
			cell.setName("12");

			SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
			tstCntrlr.setGameDataModel(tstGmDat);
			tstCntrlr.setMap(tstMap);
			tstCntrlr.setSelectedTower("lblTwr1");
			tstCntrlr.setSelectedCell(cell);
			tstCntrlr.RemoveTower();

			assertTrue(tstCntrlr.gameDataModel.getAccountBalance() == 100 + (new TowerCastle()).getRefundValue());
	}

	/**
	 * Test to fail to upgrade a tower with low account balance.
	 */
	@Test
	public void testUpgradeLowBalnc() {
		GameData tstGmDat = new GameData();
		tstGmDat.setAccountBalance(0);

		GameMap tstMap = new GameMap();
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			tstMap.initialize("testmap", mapArray);
			tstMap.addTower("12", new TowerCastle());

			JPanel cell = new JPanel();
			cell.setName("12");

			SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
			tstCntrlr.setGameDataModel(tstGmDat);
			tstCntrlr.setMap(tstMap);
			tstCntrlr.setSelectedTower("lblTwr1");
			tstCntrlr.setSelectedCell(cell);
			tstCntrlr.upgradeSelectedTower();

			assertTrue(tstCntrlr.getSelectedTower().getCurrentLevel() == 1);
	}

	/**
	 * Test to upgrade a tower successfully with enough account balance.
	 */
	@Test
	public void testUpgradeWithBalnc() {
		GameData tstGmDat = new GameData();
		tstGmDat.setAccountBalance(20);

		GameMap tstMap = new GameMap();
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			tstMap.initialize("testmap", mapArray);
			tstMap.addTower("12", new TowerCastle());

			JPanel cell = new JPanel();
			cell.setName("12");

			SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
			tstCntrlr.setGameDataModel(tstGmDat);
			tstCntrlr.setMap(tstMap);
			tstCntrlr.setSelectedTower("lblTwr1");
			tstCntrlr.setSelectedCell(cell);
			tstCntrlr.upgradeSelectedTower();

			assertTrue(tstCntrlr.getSelectedTower().getCurrentLevel() == 2);
	}
}
