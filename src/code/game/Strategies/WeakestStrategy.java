package code.game.Strategies;

import code.game.Models.GameMap;
import code.game.Models.TowerModel;

public class WeakestStrategy implements TowerStrategy {

	public String StrategyName="Weakest First";
	public void ShootCritters(GameMap map, TowerModel tower)
	{
		
	}
	
	public String GetStrategyName(){
		return StrategyName;
	}
}
