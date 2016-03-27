package code.game.tests;

import java.io.File;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import code.game.controllers.SingleGameController;
import code.game.models.MapModel;
import code.game.utils.Util;

/**
 * Test case class to test all the logging methods of map logging
 * 
 * @author Armaghan
 *
 */
public class MapLoggerTest {


	/**
	 * creates a new map file, adds a creation date, writes it and tests if the creation time was updated
	 */
	@Test
	public void testMapFileCreationDate() {

		MapModel mapModel = new MapModel();

		mapModel.setFilePath(new File(Util.getMapsDirectory() + "testMap3.map"));
		mapModel.setCreationTime(Util.getDate());

		Util.updateMapFile(mapModel);

		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap3.map"));
		String creationDate = testController.getMapModel().getCreationTime();

		Assert.assertNotNull(creationDate);
	}


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

		// Umer / Lokesh please have a look whats wrong with this method
		final ArrayList<String> playHistory3 =  playHistory1;

		playHistory1.add(Util.getDate() + " -- Scores: 54 ");
		testController.getMapModel().setPlayHistory(playHistory1);

		Util.updateMapFile(testController.getMapModel());

		SingleGameController testController2 = SingleGameController.getGameControllerInstance();
		testController2.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> playHistory2 = testController2.getMapModel().getPlayHistory();

		// Umer / Lokesh please have a look whats wrong with this method
		Assert.assertNotEquals(playHistory3, playHistory2);
	}

	/**
	 * reads a map file, adds a another score entry in top scores, writes it back and tests if the file was updated
	 */
	@Test
	public void testMapTopFiveScores() {

		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> topFiveScores1 = testController.getMapModel().getTopFiveScores();

		// Umer / Lokesh please have a look whats wrong with this method
		final ArrayList<String> topFiveScores3 =  topFiveScores1;

		topFiveScores1.add(Util.getDate() + " -- Scores: 59 ");
		testController.getMapModel().setTopFiveScores(topFiveScores1);

		Util.updateMapFile(testController.getMapModel());

		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> topFiveScores2 = testController.getMapModel().getTopFiveScores();

		// Umer / Lokesh please have a look whats wrong with this method
		Assert.assertNotEquals(topFiveScores3, topFiveScores2);
	}
}
