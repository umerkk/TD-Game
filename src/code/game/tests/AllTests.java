package code.game.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CritterFactoryTest.class, GameMapTest.class, NearestStrategyTest.class, SingleGameControllerTest.class,
		StrongestStrategyTest.class, TDGameMain2Test.class, WeakestStrategyTest.class })
public class AllTests {

}
