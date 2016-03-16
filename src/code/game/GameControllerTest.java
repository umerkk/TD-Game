package code.game;

import static org.junit.Assert.*;

import org.junit.Test;

import code.game.Models.CastleTower;
import code.game.Models.TowerModel;
import junit.framework.Assert;

/**
 * 
 * @author Lokesh
 * @author Alaa
 *
 */

public class GameControllerTest
{

	@Test
	public void testgetSelectdTowerDetails()
	{
		TowerModel twrmdlobj = new CastleTower();
		twrmdlobj.upgradeCurrentLevel();
		String expctdTwrDetails = "Name : Castle Tower\nLevel : 2\nCost of Tower : 100\nUpgrade Cost : 10\nRefund Value : 90\n" 
						+ "Range : 4\nPower : 22.0\nRate of Fire : 10\nHealth : 100";
		assertEquals(expctdTwrDetails, twrmdlobj.getTowerDetails().toString());
	}
	
	@Test 
	public void testaddSelectdTower()
	{
		GameController gamcntrlobj = new GameController();
		gamcntrlobj.setAccBalnc(120);
		TowerModel selctdmodl = new CastleTower();
		if(gamcntrlobj.getAccountBalnc() - selctdmodl.getCostOfTower() > 0)
		{
			gamcntrlobj.setAccBalnc(gamcntrlobj.getAccountBalnc() - selctdmodl.getCostOfTower());
		}
		assertEquals(20, gamcntrlobj.getAccountBalnc());
	}
	
	@Test
	public void testRemoveSelctdTower()
	{
		String cell = "12";
		GameController gamcntrlobj = new GameController();
		gamcntrlobj.initializeCntrolr();
		gamcntrlobj.setAccBalnc(20);
		TowerModel selctdmodl = new CastleTower();
		gamcntrlobj.addTower(selctdmodl, cell);
		gamcntrlobj.removeSelctdTower(cell);
		assertEquals(100, gamcntrlobj.getAccountBalnc());
	}

	@Test
	public void testUpgrdBtnHandlr() 
	{
		String cell = "12";
		GameController gamcntrlobj = new GameController();
		gamcntrlobj.initializeCntrolr();
		gamcntrlobj.setAccBalnc(20);
		TowerModel selctdmodl = new CastleTower();
		gamcntrlobj.addTower(selctdmodl, cell);
		gamcntrlobj.setSelectedTower(selctdmodl, false);
		gamcntrlobj.upgrdBtnHandlr();
		assertEquals(10, gamcntrlobj.getAccountBalnc());
	}

}
