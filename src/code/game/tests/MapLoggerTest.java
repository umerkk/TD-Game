package code.game.tests;

import java.io.File;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import code.game.controllers.SingleGameController;
import code.game.utils.Util;

/**
 * Test case class to test all the logging methods of mad logging
 * 
 * @author Armaghan
 *
 */
public class MapLoggerTest {

	/**
	 * reads a map file, modifies its edit time, writes it back and tests if the file was updated
	 */
	@Test
	public void testMapEditTime() {

		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		String editTime1 = testController.getMapModel().getEditTime();

		testController.getMapModel().setEditTime(Util.getDate());

		Util.updateMapFile(testController.getMapModel());

		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		String editTime2 = testController.getMapModel().getEditTime();

		Assert.assertNotEquals(editTime1, editTime2);
	}

	/**
	 * reads a map file, adds a gameplay event, writes it back and tests if the file was updated
	 */
	@Test
	public void testMapPlayHistory() {

		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> playHistory1 = testController.getMapModel().getPlayHistory();

		playHistory1.add(Util.getDate() + " -- Scores: 54 ");
		testController.getMapModel().setPlayHistory(playHistory1);

		Util.updateMapFile(testController.getMapModel());

		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> playHistory2 = testController.getMapModel().getPlayHistory();

		Assert.assertNotEquals(playHistory1, playHistory2);
	}

	/**
	 * reads a map file, adds a another score entry in top scores, writes it back and tests if the file was updated
	 */
	@Test
	public void testMapTopFiveScores() {

		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> topFiveScores1 = testController.getMapModel().getTopFiveScores();

		topFiveScores1.add(Util.getDate() + " -- Scores: 59 ");
		testController.getMapModel().setTopFiveScores(topFiveScores1);

		Util.updateMapFile(testController.getMapModel());

		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> topFiveScores2 = testController.getMapModel().getTopFiveScores();

		Assert.assertNotEquals(topFiveScores1, topFiveScores2);
	}


}
