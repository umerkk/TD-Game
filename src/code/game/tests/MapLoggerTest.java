package code.game.tests;

import static org.junit.Assert.*;

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
 * @author Iftikhar
 * @version 1.0.0
 */
public class MapLoggerTest {

	/**
	 * reads a map file, adds a gameplay event, writes it back and tests if the file was updated
	 */
	@Test
	public void testMapPlayHistory() {

		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> playHistory1 = testController.getMapModel().getPlayHistory();

		String playHist = Util.getDate() + " -- Scores: 54 ";
		testController.getMapModel().getPlayHistory().add(playHist);
		
		// Umer / Lokesh please have a look whats wrong with this method
		Util.updateMapFile(testController.getMapModel());

		SingleGameController testController2 = SingleGameController.getGameControllerInstance();
		testController2.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> playHistory2 = testController2.getMapModel().getPlayHistory();

		// Umer / Lokesh please have a look whats wrong with this method
		assertTrue(testController.getMapModel().getPlayHistory().contains(playHist));
	}

	/**
	 * reads a map file, adds a another score entry in top scores, writes it back and tests if the file was updated
	 */
	@Test
	public void testMapTopFiveScores() {

		String topScore = "";
		SingleGameController testController = SingleGameController.getGameControllerInstance();
		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		
		topScore = Util.getDate() + " -- Scores: 59 ";
		testController.getMapModel().getTopFiveScores().add(topScore);
		
		// Umer / Lokesh please have a look whats wrong with this method
		Util.updateMapFile(testController.getMapModel());

		testController.readFromFile(new File(Util.getMapsDirectory() + "testMap2.map"));
		ArrayList<String> topFiveScores2 = testController.getMapModel().getTopFiveScores();

		assertTrue(testController.getMapModel().getTopFiveScores().contains(topScore));
	}
}
