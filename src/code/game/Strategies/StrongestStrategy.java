package code.game.Strategies;

import code.game.Models.Critter;
import code.game.Models.GameMap;
import code.game.Models.TowerModel;

public class StrongestStrategy implements TowerStrategy{

	public String StrategyName="Strongest First";
	Critter strongestCritter=null;
	public boolean ShootCritters(GameMap map, TowerModel tower)
	{
		char[] name_exploded = tower.GetMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		boolean isHit=false;
		

		for(int k=1;k<=tower.getCurrentLevel();k++)
		{

			String xRight = String.valueOf(x+k);
			String xLeft = String.valueOf(x-k);
			String yUp = String.valueOf(y+k);
			String yDown = String.valueOf(y-k);
			try {
				if(map.CheckCritterExists(xRight+name_exploded[1]))
				{
					if(strongestCritter==null)
					{
						strongestCritter = map.GetCritter(xRight+name_exploded[1]);

					} else if(strongestCritter.GetHealth() < map.GetCritter(xRight+name_exploded[1]).GetHealth())
					{
						strongestCritter = map.GetCritter(xRight+name_exploded[1]);
					}


				} 
				if(map.CheckCritterExists(xLeft+name_exploded[1]))
				{

					if(strongestCritter==null)
					{
						strongestCritter = map.GetCritter(xLeft+name_exploded[1]);

					} else if(strongestCritter.GetHealth() < map.GetCritter(xLeft+name_exploded[1]).GetHealth())
					{
						strongestCritter = map.GetCritter(xLeft+name_exploded[1]);
					}



				} 
				if(map.CheckCritterExists(name_exploded[0]+yUp))
				{
					if(strongestCritter==null)
					{
						strongestCritter = map.GetCritter(name_exploded[0]+yUp);

					} else if(strongestCritter.GetHealth() < map.GetCritter(name_exploded[0]+yUp).GetHealth())
					{
						strongestCritter = map.GetCritter(name_exploded[0]+yUp);
					}



				} 
				if(map.CheckCritterExists(name_exploded[0]+yDown))
				{
					if(strongestCritter==null)
					{
						strongestCritter = map.GetCritter(name_exploded[0]+yDown);

					} else if(strongestCritter.GetHealth() < map.GetCritter(name_exploded[0]+yDown).GetHealth())
					{
						strongestCritter = map.GetCritter(name_exploded[0]+yDown);
					}

				}

			} catch (IndexOutOfBoundsException e)
			{
				continue;
			}

		}
		if(strongestCritter!=null)
		{
			strongestCritter.ReduceHealth((int)tower.getPowerOfBullets());
			isHit=true;
		}
		return isHit;

	}

	public String GetStrategyName(){
		return StrategyName;
	}
}
