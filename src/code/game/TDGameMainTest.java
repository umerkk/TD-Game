package code.game;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TDGameMainTest {

	@Test
	public void testReadMapFrmFile()
	{
		int[][] maparr =  new int[][]{ { 0, 1, 0, 0 },
			{ 0, 2, 3, 0},
			{ 0, 0, 4, 0},
			{ 0, 0, 9999, 0}}; 
		
		TDGameMain tdgameobj = new TDGameMain(new GameController());
		int[][] readarr = tdgameobj.readMapFrmFile();
		
		Assert.assertArrayEquals(maparr, readarr);

	}

}
