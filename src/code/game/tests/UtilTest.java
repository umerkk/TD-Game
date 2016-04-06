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
 * @author Lokesh
 * @version 1.0.0
 */
public class UtilTest {

	/**
	 * tests if the method returns a correct default file path by reading a file and checking it
	 */
	@Test
	public void testDefaultFilePath() {

		MapModel mapModel = null;
		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getDefaultPath() + "maps/testmap.map"));
		mapModel = testController.getMapModel();

		Assert.assertNotNull(mapModel);
	}

	/**
	 * tests if the method returns a correct logging file path by reading a file and checking it
	 */
	@Test
	public void testLogFilePath() {

		String readLog = null;
		Util.logGlobal("Sample global log from test case.", false); // generating a sample log file
		readLog = Util.readLog(Util.getLogsPath()+ "/"+ Util.FILE_LOG_GLOBAL + ".txt");

		if(readLog.length()<1){ // makes sure that the read content is not empty
			readLog = null;
		}

		Assert.assertNotNull(readLog);

	}

	/**
	 * tests if the method wave logging is working correctly
	 */
	@Test
	public void tesWaveLog() {

		Assert.assertTrue(Util.logWave("Sample wave log from test case."));
	}

	/**
	 * tests if the individual tower logging is working correctly
	 */
	@Test
	public void testTowerLog() {

		Assert.assertTrue(Util.logTower("Castle Tower", "Sample individual tower log from test case."));
	}

	/**
	 * tests if the collective tower logging is working correctly
	 */
	@Test
	public void testCollectiveTowerLog() {

		Assert.assertTrue(Util.logTowerCollective("Sample collective tower log from test case.", false));
	}

	/**
	 * tests if the global logging is working correctly
	 */
	@Test
	public void testGlobalLog() {

		Assert.assertTrue(Util.logGlobal("Sample global log from test case.", false));
	}

	/**
	 * tests if global log file was fetched correctly
	 */
	@Test
	public void testReadGlobalLog() {

		String readLog = null;
		String testStr = "Sample global log from test case.";
		Util.logGlobal(testStr, false); // generating a log file
		readLog = Util.readLog(Util.getFilePath(Util.FILE_LOG_GLOBAL));

		Assert.assertTrue(readLog.contains(testStr));
	}

	/**
	 * tests if global log file was fetched successfully
	 */
	@Test
	public void testReadWaveLog() {

		String readLog = null;
		String testStr = "Sample wave log from test case.";
		Util.logWave(testStr); // generating a log file
		readLog = Util.readLog(Util.getFilePath(Util.FILE_LOG_WAVE));

		Assert.assertTrue(readLog.contains(testStr));
	}

	/**
	 * tests if global log file was fetched successfully
	 */
	@Test
	public void testReadTowerLog() {

		String readLog = null;
		String testStr = "Sample tower log from test case.";
		Util.logTower("Castle Tower", testStr); // generating a log file
		readLog = Util.readLog(Util.getFilePath("Castle Tower"));

		Assert.assertTrue(readLog.contains(testStr));
	}

	/**
	 * tests if collective log file was fetched successfully
	 */
	@Test
	public void testReadCollectiveTowerLog() {

		String readLog = null;
		String testStr = "Sample collective tower log from test case.";
		Util.logGlobal(testStr, false); // generating a log file
		readLog = Util.readLog(Util.getFilePath(Util.FILE_LOG_TOWER_COLLECTIVE));

		Assert.assertTrue(readLog.contains(testStr));
	}

	/**
	 * reads a map file, adds a gameplay event, writes it back and tests if the method getPlayHistory() 
	 * successfully converts history to string
	 */
	@Test
	public void testGetPlayHistory() {

		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getMapsDirectory() + "testmap2.map"));

		String testStr = Util.getDate() + " -- Scores: 54 ";
		testController.getMapModel().getPlayHistory().add(testStr);
		Util.updateMapFile(testController.getMapModel());

		testController.readFromFile(new File(Util.getMapsDirectory() + "testmap2.map"));

		String history = Util.getPlayHistory(testController.getMapModel().getPlayHistory());

		Assert.assertTrue(history.contains(testStr));
	}

}
