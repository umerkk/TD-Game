package code.game.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CritterFactoryTest.class, GameMapTest.class, MapLoggerTest.class, SingleGameControllerTest.class,
		StrategyNearestTest.class, StrategyNearestToEndPointTest.class, StrategyStrongestTest.class,
		StrategyWeakestTest.class, TDGameMain2Test.class, UtilTest.class })
public class AllTests {

}
