package code.game.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.game.TDGameMain2;
import code.game.models.GameData;
import code.game.models.GameMap;

public class TDGameMain2Test {

	@Test
	public void testObserverPtrn() 
	{
		GameData tstGmDat = new GameData();
		GameMap tstMap = new GameMap();
		TDGameMain2 tstWindow = new TDGameMain2(tstGmDat,tstMap);

		tstGmDat.addObserver(tstWindow);
		tstGmDat.setAccountBalance(300);
		
		assertTrue(tstWindow.getAccBal().equals("300"));
	}

}
