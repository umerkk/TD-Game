package code.game.strategies;

import java.io.Serializable;

import code.game.models.GameMap;
import code.game.models.TowerModel;

/**
 * an interface to implement tower's strategy pattern
 * @author Armaghan
 * @version 1.0.0
 */
public interface TowerStrategy extends Serializable {
	public boolean shootCritters(GameMap map, TowerModel tower);
	public String getStrategyName();
}
