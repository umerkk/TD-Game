package code.game.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import code.game.utils.Util;

/**
 * Test suite class for testing all the test cases
 * @author lokesh
 * @author Armaghan
 * @version 1.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({ CritterFactoryTest.class, GameMapTest.class, StrategyNearestTest.class, SingleGameControllerTest.class,
		StrategyStrongestTest.class, TDGameMain2Test.class, StrategyWeakestTest.class, UtilTest.class, MapLoggerTest.class })
public class AllTests {

}
