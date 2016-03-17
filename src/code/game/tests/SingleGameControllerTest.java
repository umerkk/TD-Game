package code.game.tests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JPanel;

import org.junit.Assert;
import org.junit.Test;

import code.game.controllers.SingleGameController;
import code.game.models.CastleTower;
import code.game.models.GameData;
import code.game.models.GameMap;

public class SingleGameControllerTest {

	@Test
	public void testReadMap() 
	{
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 
		
		
		SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
		tstCntrlr.readFrmFile(new File(System.getProperty("user.dir") + "/maps/testmap.map"));
		Assert.assertArrayEquals(mapArray, tstCntrlr.getMapModl().getMapArray());
	}

	@Test
	public void testcheckSingltn() 
	{
		SingleGameController tstCntrlr1 = SingleGameController.getGameControllerInstance();
		SingleGameController tstCntrlr2 = SingleGameController.getGameControllerInstance();
		
		assertTrue(tstCntrlr1.toString().equals(tstCntrlr2.toString()));
	}
	
	@Test
	public void testSellTower() 
	{
		GameData tstGmDat = new GameData();
		tstGmDat.setAccountBalance(100);
		
		GameMap tstMap = new GameMap();
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 
		
		tstMap.initialize("testmap", mapArray);
		tstMap.addTower("12", new CastleTower());
		
		JPanel cell = new JPanel();
		cell.setName("12");
		
		SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
		tstCntrlr.setGameDataModel(tstGmDat);
		tstCntrlr.setMap(tstMap);
		tstCntrlr.setSelectedTower("lblTwr1");
		tstCntrlr.setSelectedCell(cell);
		tstCntrlr.RemoveTower();
		
		assertTrue(tstCntrlr.gameDataModel.getAccountBalance() == 100 + (new CastleTower()).getRefundValue());
	}

	@Test
	public void testUpgradeLowBalnc() 
	{
		GameData tstGmDat = new GameData();
		tstGmDat.setAccountBalance(0);
		
		GameMap tstMap = new GameMap();
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 
		
		tstMap.initialize("testmap", mapArray);
		tstMap.addTower("12", new CastleTower());
		
		JPanel cell = new JPanel();
		cell.setName("12");
		
		SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
		tstCntrlr.setGameDataModel(tstGmDat);
		tstCntrlr.setMap(tstMap);
		tstCntrlr.setSelectedTower("lblTwr1");
		tstCntrlr.setSelectedCell(cell);
		tstCntrlr.upgradeSelectedTower();
		
		assertTrue(tstCntrlr.getSelectedTwr().getCurrentLevel() == 1);
	}
	
	@Test
	public void testUpgradeWithBalnc() 
	{
		GameData tstGmDat = new GameData();
		tstGmDat.setAccountBalance(20);
		
		GameMap tstMap = new GameMap();
		int[][] mapArray =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 0, 0},
			{ 0, 3, 4, 0},
			{ 0, 0, 9999, 0}}; 
		
		tstMap.initialize("testmap", mapArray);
		tstMap.addTower("12", new CastleTower());
		
		JPanel cell = new JPanel();
		cell.setName("12");
		
		SingleGameController tstCntrlr = SingleGameController.getGameControllerInstance();
		tstCntrlr.setGameDataModel(tstGmDat);
		tstCntrlr.setMap(tstMap);
		tstCntrlr.setSelectedTower("lblTwr1");
		tstCntrlr.setSelectedCell(cell);
		tstCntrlr.upgradeSelectedTower();
		
		assertTrue(tstCntrlr.getSelectedTwr().getCurrentLevel() == 2);
	}
}
