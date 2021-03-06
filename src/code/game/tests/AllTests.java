package code.game.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for all the 35 test cases of the main game. 
 * @author Iftikhar
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ CritterFactoryTest.class, GameMapTest.class, MapLoggerTest.class, SingleGameControllerTest.class,
		StrategyNearestTest.class, StrategyNearestToEndPointTest.class, StrategyStrongestTest.class,
		StrategyWeakestTest.class, TDGameMain2Test.class, UtilTest.class })
public class AllTests {

}
