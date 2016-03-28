package code.game.strategies;

import code.game.models.GameMap;
import code.game.models.TowerModel;

/**
 * an interface to implement tower's strategy pattern
 * @author Armaghan
 *
 */
public interface TowerStrategy {
	public boolean shootCritters(GameMap map, TowerModel tower);
	public String getStrategyName();
}
