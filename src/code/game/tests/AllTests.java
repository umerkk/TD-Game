package code.game.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import code.game.utils.Util;

/**
 * Test suite class for testing all the test cases
 * @author lokesh
 * @author Armaghan
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ CritterFactoryTest.class, GameMapTest.class, NearestStrategyTest.class, SingleGameControllerTest.class,
		StrongestStrategyTest.class, TDGameMain2Test.class, WeakestStrategyTest.class, UtilTest.class, MapLoggerTest.class })
public class AllTests {

}
