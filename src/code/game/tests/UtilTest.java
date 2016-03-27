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
	 * tests if the method returns a correct default file path by reading a file and checking it
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
	 * tests if the method wave logging is working correctly
	 */
	@Test
	public void tesWaveLog() {
		
		boolean wasLoggedSuccessfully = Util.logWave("Sample wave log from test case.");
		
		Assert.assertTrue(wasLoggedSuccessfully);
	}

	/**
	 * tests if the individual tower logging is working correctly
	 */
	@Test
	public void testTowerLog() {

		boolean wasLoggedSuccessfully = Util.logTower("Castle Tower", "Sample individual tower log from test case.");

		Assert.assertTrue(wasLoggedSuccessfully);
	}
	
	/**
	 * tests if the collective tower logging is working correctly
	 */
	@Test
	public void testCollectiveTowerLog() {

		boolean wasLoggedSuccessfully = Util.logTowerCollective("Sample collective tower log from test case.", false);

		Assert.assertTrue(wasLoggedSuccessfully);
	}
	
	/**
	 * tests if the global logging is working correctly
	 */
	@Test
	public void testGlobalLog() {

		boolean wasLoggedSuccessfully = Util.logGlobal("Sample global log from test case.", false);

		Assert.assertTrue(wasLoggedSuccessfully);
	}
	
	/**
	 * tests if global log file was fetched correctly
	 */
	@Test
	public void testReadGlobalLog() {

		String readLog = null;
		Util.logGlobal("Sample global log from test case.", false); // generating a log file
		readLog = Util.readLog(Util.getFilePath(Util.FILE_LOG_GLOBAL));

		Assert.assertNotNull(readLog);
	}
	
	/**
	 * tests if global log file was fetched successfully
	 */
	@Test
	public void testReadWaveLog() {

		String readLog = null;
		Util.logWave("Sample wave log from test case."); // generating a log file
		readLog = Util.readLog(Util.getFilePath(Util.FILE_LOG_WAVE));

		Assert.assertNotNull(readLog);
	}
	
	/**
	 * tests if global log file was fetched successfully
	 */
	@Test
	public void testReadTowerLog() {

		String readLog = null;
		Util.logTower("Castle Tower", "Sample tower log from test case."); // generating a log file
		readLog = Util.readLog(Util.getFilePath("Castle Tower"));

		Assert.assertNotNull(readLog);
	}
	
	/**
	 * tests if collective log file was fetched successfully
	 */
	@Test
	public void testReadCollectiveTowerLog() {

		String readLog = null;
		Util.logGlobal("Sample collective tower log from test case.", false); // generating a log file
		readLog = Util.readLog(Util.getFilePath(Util.FILE_LOG_TOWER_COLLECTIVE));

		Assert.assertNotNull(readLog);
	}
	
}
