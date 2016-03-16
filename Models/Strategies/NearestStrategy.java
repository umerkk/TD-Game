package Strategies;

import code.game.Models.GameMap;
import code.game.Models.TowerModel;

public class NearestStrategy  implements TowerStrategy {

	public String StrategyName="Nearest First";
	public void ShootCritters(GameMap map, TowerModel tower)
	{
		char[] name_exploded = tower.getName().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		
		for(int k=tower.getCurrentLevel();k>0;k--)
		{
			
			
			
		}
		
		
		
		
	}
	
	public String GetStrategyName(){
		return StrategyName;
	}
}
