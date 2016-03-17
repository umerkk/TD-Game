package code.game.strategies;

import code.game.models.Critter;
import code.game.models.GameMap;
import code.game.models.TowerModel;

public class NearestStrategy  implements TowerStrategy {

	public String StrategyName="Nearest First";
	Critter lockedCritter=null;
	
	public boolean ShootCritters(GameMap map, TowerModel tower)
	{
		char[] name_exploded = tower.GetMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		Boolean isIgnore=false;
		boolean isHit=false;
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
						lockedCritter=map.GetCritter(xRight+name_exploded[1]);
						isIgnore = true;
						isHit=true;
					} else if(map.CheckCritterExists(xLeft+name_exploded[1]))
					{
						map.GetCritter(xLeft+name_exploded[1]).ReduceHealth((int)tower.getPowerOfBullets());
						lockedCritter=map.GetCritter(xRight+name_exploded[1]);
						isIgnore = true;
						isHit=true;
					} else if(map.CheckCritterExists(name_exploded[0]+yUp))
					{
						map.GetCritter(name_exploded[0]+yUp).ReduceHealth((int)tower.getPowerOfBullets());
						lockedCritter=map.GetCritter(xRight+name_exploded[1]);
						isIgnore = true;
						isHit=true;

					} else if(map.CheckCritterExists(name_exploded[0]+yDown))
					{
						map.GetCritter(name_exploded[0]+yDown).ReduceHealth((int)tower.getPowerOfBullets());
						lockedCritter=map.GetCritter(xRight+name_exploded[1]);
						isIgnore = true;
						isHit=true;

					}
				} catch (IndexOutOfBoundsException e)
				{
					continue;
				}
			}
		}
		return isHit;
	}

	public String GetStrategyName(){
		return StrategyName;
	}
}


