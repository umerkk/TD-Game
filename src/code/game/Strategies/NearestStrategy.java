package code.game.Strategies;

import code.game.Models.GameMap;
import code.game.Models.TowerModel;

public class NearestStrategy  implements TowerStrategy {

	public String StrategyName="Nearest First";
	public void ShootCritters(GameMap map, TowerModel tower)
	{
		char[] name_exploded = tower.GetMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		Boolean isIgnore=false;
		for(int k=1;k<=tower.getCurrentLevel();k++)
		{
			String xRight = String.valueOf(x+k);
			String xLeft = String.valueOf(x-k);
			String yUp = String.valueOf(y+k);
			String yDown = String.valueOf(y-k);
			if(!isIgnore)
			{
				try {
					if(map.CheckCritterExists(xRight+name_exploded[1]))
					{
						map.GetCritter(xRight+name_exploded[1]).ReduceHealth((int)tower.getPowerOfBullets());
						isIgnore = true;
					} else if(map.CheckCritterExists(xLeft+name_exploded[1]))
					{
						map.GetCritter(xLeft+name_exploded[1]).ReduceHealth((int)tower.getPowerOfBullets());
						isIgnore = true;
					} else if(map.CheckCritterExists(name_exploded[0]+yUp))
					{
						map.GetCritter(name_exploded[0]+yUp).ReduceHealth((int)tower.getPowerOfBullets());
						isIgnore = true;

					} else if(map.CheckCritterExists(name_exploded[0]+yDown))
					{
						map.GetCritter(name_exploded[0]+yDown).ReduceHealth((int)tower.getPowerOfBullets());
						isIgnore = true;

					}
				} catch (IndexOutOfBoundsException e)
				{
					continue;
				}
			}
		}
	}

	public String GetStrategyName(){
		return StrategyName;
	}
}


