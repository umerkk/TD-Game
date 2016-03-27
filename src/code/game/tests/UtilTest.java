package code.game.tests;

import java.io.File;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import code.game.controllers.SingleGameController;
import code.game.models.MapModel;
import code.game.utils.Util;

/**
 * Test case class to test all the logging methods of game logging
 * 
 * @author Armaghan
 *
 */
public class UtilTest {

	/**
	 * checks if the method returns a correct default file path by reading a file and checking it
	 */
	@Test
	public void testDefaultFilePath() {

		MapModel mapModel = null;
		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getDefaultPath() + "maps/testMap3.map"));
		mapModel = testController.getMapModel();

		Assert.assertNotNull(mapModel);
	}

	/**
	 * checks if the method wave logging is working correctly
	 */
	@Test
	public void tesWaveLog() {
		
		boolean wasLoggedSuccessfully = Util.logWave("Sample wave log from test case.");
		
		Assert.assertTrue(wasLoggedSuccessfully);
	}

	/**
	 * checks if the individual tower logging is working correctly
	 */
	@Test
	public void testTowerLog() {

		boolean wasLoggedSuccessfully = Util.logTower("Castle Tower", "Sample individual tower log from test case.");

		Assert.assertTrue(wasLoggedSuccessfully);
	}
	
	/**
	 * checks if the global logging is working correctly
	 */
	@Test
	public void testGlobalLog() {

		boolean wasLoggedSuccessfully = Util.logGlobal("Sample global log from test case.", true);

		Assert.assertTrue(wasLoggedSuccessfully);
	}
}
