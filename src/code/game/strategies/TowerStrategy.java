package code.game.strategies;

import code.game.models.GameMap;
import code.game.models.TowerModel;

public interface TowerStrategy {

	public String StrategyName="None";
	public boolean ShootCritters(GameMap map,TowerModel tower);
	public String GetStrategyName();
	
}
