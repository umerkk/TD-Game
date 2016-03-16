package code.game.Strategies;

import code.game.Models.GameMap;
import code.game.Models.TowerModel;

public class NearestStrategy  implements TowerStrategy {

	public String StrategyName="Nearest First";
	public void ShootCritters(GameMap map, TowerModel tower)
	{
		char[] name_exploded = tower.getName().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		
		for(int k=1;k<=tower.getCurrentLevel();k++)
		{

			String xRight = String.valueOf(x+k);
			String xLeft = String.valueOf(x-k);
			String yUp = String.valueOf(y+k);
			String yDown = String.valueOf(y-k);
			
			if(map.CheckCritterExists(xRight+name_exploded[1]))
			{
				
			} else if(map.CheckCritterExists(xLeft+name_exploded[1]))
			{
				
			} else if(map.CheckCritterExists(name_exploded[0]+yUp))
			{
				
			} else if(map.CheckCritterExists(name_exploded[0]+yDown))
			{
				
			}
		}
		
		
	}
	
	public String GetStrategyName(){
		return StrategyName;
	}
}
