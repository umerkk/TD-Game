package code.game.tests;

import static org.junit.Assert.assertTrue;

import java.awt.Panel;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Assert;
import org.junit.Test;

import code.game.TDGameMain2;
import code.game.controllers.SingleGameController;
import code.game.models.TowerCastle;
import code.game.models.TowerModel;
import code.game.strategies.StrategyNearest;
import code.game.strategies.StrategyWeakest;
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
		int[][] tstMapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			SingleGameController testController = SingleGameController.getGameControllerInstance();
			testController.readFromFile(new File(Util.getMapsDirectory() + "testmap2.map"));

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
			tstCntrlr.removeTower();

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

	/**
	 * Try to save and load back the current map data in the game to test partial functionality of
	 * save/load game.
	 */
	@Test
	public void testSaveMapData() {

		GameMap tstMap = new GameMap();
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 

			tstMap.initialize("testmap", mapArray);
			tstMap.addTower("12", new TowerCastle());

			SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
			tstCntrlr.setMap(tstMap);

			File file = new File("SavedGames//testGameFile.gameData");
			File fileView = new File("SavedGames//testGameFile.gameView");
			tstCntrlr.saveGameData(new Panel(), file, fileView);

			TDGameMain2 tstWindow = new TDGameMain2(new GameData(), new GameMap());
			tstCntrlr = tstWindow.loadSavedGame(file);

			mapArray =  new int[][]{ { 0, 1, 0, 0 },
				{ 0, 2, -7, 0},
				{ 0, 3, 4, 0},
				{ 0, 0, 9999, 0}};

				Assert.assertArrayEquals(mapArray, tstCntrlr.getMapModl().getMapArray());
	}

	/**
	 * Try to save and load back the current game data in the game to test partial functionality of
	 * save/load game.
	 */
	@Test
	public void testSaveGameData() {

		GameData tstGmDat = new GameData();
		tstGmDat.setAccountBalance(99);
		SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
		tstCntrlr.setGameDataModel(tstGmDat);
		File file = new File("SavedGames//testGameFile.gameData");
		File fileView = new File("SavedGames//testGameFile.gameView");
		tstCntrlr.saveGameData(new Panel(), file, fileView);

		TDGameMain2 tstWindow = new TDGameMain2(new GameData(), new GameMap());
		tstCntrlr = tstWindow.loadSavedGame(file);

		assertTrue(tstCntrlr.gameDataModel.getAccountBalance() == 99);

	}

	/**
	 * Check if the current score is recorded in map file. (Integration test)
	 */
	@Test
	public void testUpdatScore() {
		GameData tstGmDat = new GameData();
		tstGmDat.setAccountBalance(100);
		tstGmDat.setPlayerPower(5);

		SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
		tstCntrlr.setMap(new GameMap());
		tstCntrlr.readFromFile(new File(Util.getMapsDirectory() + "testmap2.map"));
		tstCntrlr.setGameDataModel(tstGmDat);
		tstCntrlr.calculateAndUpdateScores();

		tstCntrlr.readFromFile(new File(Util.getMapsDirectory() + "testmap2.map"));
		String lastLog = tstCntrlr.getMapModel().getPlayHistory().get(tstCntrlr.getMapModel().getPlayHistory().size() - 1);
		CharSequence charSeq = "Gameplay score:: 500";
		assertTrue(lastLog.contains(charSeq));
	}

	/**
	 * Check if when a strategy is changed, a corresponding log entry is produced. (Integration test)
	 */
	@Test
	public void testStratgyChang() {
		SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();

		tstCntrlr.setSelectedTower("lblTwr1");
		TowerModel tstTwrModel = tstCntrlr.getSelectedTower(); 
		tstTwrModel.setStrategy(new StrategyWeakest(), new GameMap());

		boolean writeLog = tstCntrlr.setNewStrategy(0);

		assertTrue(writeLog && tstTwrModel.getStrategy() instanceof StrategyNearest);

	}
}
