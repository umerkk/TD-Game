package code.game.strategies;

import code.game.models.Critter;
import code.game.models.GameMap;
import code.game.models.TowerModel;

/**
 * A Strategy design pattern class implementing Tower's strategy named Weakest strategy
 * 
 * @author Umer
 * 
 */
public class StrategyWeakest implements TowerStrategy {

	public String StrategyName="Weakest First";
	public Critter weakestCritter=null;
	
	/**
	 * Shoots critters by deciding, which critter is the weakest in the range.
	 * if finds a weak critter in the range, it open's fire
	 * and returns the confirmation if the intended critter has hit or not
	 */
	public boolean shootCritters(GameMap map, TowerModel tower){
		char[] name_exploded = tower.getMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		boolean isHit=false;
		weakestCritter=null;
		
		for(int k=1;k<=tower.getCurrentLevel();k++){
			try {
				String xRight = String.valueOf(x+k);
				String xLeft = String.valueOf(x-k);
				String yUp = String.valueOf(y+k);
				String yDown = String.valueOf(y-k);

				if(map.checkCritterExists(xRight+name_exploded[1])){
					if(weakestCritter==null){
						weakestCritter = map.getCritter(xRight+name_exploded[1]);
					} else if(weakestCritter.getHealth() > map.getCritter(xRight+name_exploded[1]).getHealth())
					{
						weakestCritter = map.getCritter(xRight+name_exploded[1]);
					}
				} 

				if(map.checkCritterExists(xLeft+name_exploded[1])){
					if(weakestCritter==null){
						weakestCritter = map.getCritter(xLeft+name_exploded[1]);

					} else if(weakestCritter.getHealth() > map.getCritter(xLeft+name_exploded[1]).getHealth()){
						weakestCritter = map.getCritter(xLeft+name_exploded[1]);
					}
				} 

				if(map.checkCritterExists(name_exploded[0]+yUp)){
					if(weakestCritter==null){
						weakestCritter = map.getCritter(name_exploded[0]+yUp);

					} else if(weakestCritter.getHealth() > map.getCritter(name_exploded[0]+yUp).getHealth()){
						weakestCritter = map.getCritter(name_exploded[0]+yUp);
					}
				} 

				if(map.checkCritterExists(name_exploded[0]+yDown)){
					if(weakestCritter==null){
						weakestCritter = map.getCritter(name_exploded[0]+yDown);
					} else if(weakestCritter.getHealth() > map.getCritter(name_exploded[0]+yDown).getHealth()){
						weakestCritter = map.getCritter(name_exploded[0]+yDown);
					}
				}
			} catch (IndexOutOfBoundsException e){
				continue;
			}
		}

		if(weakestCritter!=null){
			weakestCritter.reduceHealth((int)tower.getPowerOfBullets());
			switch(tower.getName()){
			case "Castle Tower":{
				weakestCritter.setBackground("red");
				break;
			}
			case "Imperial Tower":{
				weakestCritter.setBackground("blue");
				break;
			}
			case "Industrial Tower":{
				weakestCritter.setBackground("black");
				break;
			}
			}
			isHit=true;
			//weakestCritter=null;
		}
		return isHit;
	}

	/**
	 * return's strategy's name
	 */
	public String getStrategyName(){
		return StrategyName;
	}
}
