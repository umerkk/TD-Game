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
	/**
	 * interface method to be implemented for the tower shooting logic
	 * @param map map on which tower is placed.
	 * @param tower tower to which the strategy is assigned.
	 * @return set if the tower strategy is successful in hitting the critter.
	 */
	public boolean shootCritters(GameMap map, TowerModel tower);

	/**
	 * Method to get the strategy name
	 * @return current strategy name.
	 */
	public String getStrategyName();
}
