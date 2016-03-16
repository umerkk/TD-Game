package code.game.Strategies;

import code.game.Models.GameMap;
import code.game.Models.TowerModel;

public interface TowerStrategy {

	public String StrategyName="None";
	public void ShootCritters(GameMap map,TowerModel tower);
	public String GetStrategyName();
	
}
