package code.game.Strategies;

import code.game.Models.GameMap;
import code.game.Models.TowerModel;

public class StrongestStrategy implements TowerStrategy{

	public String StrategyName="Strongest First";
	public void ShootCritters(GameMap map, TowerModel tower)
	{
		
	}
	
	public String GetStrategyName(){
		return StrategyName;
	}
}
