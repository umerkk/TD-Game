package code.game.strategies;

import code.game.models.Critter;
import code.game.models.GameMap;
import code.game.models.TowerModel;

/**
 * A Strategy design pattern class implementing Tower's strategy named Strongest strategy
 * 
 * @author Umer
 * 
 */
public class StrongestStrategy implements TowerStrategy{

	public String StrategyName="Strongest First";
	Critter strongestCritter=null;
	
	/**
	 * Shoots critters by deciding, which critter is the strongest in the range.
	 * if finds a strong critter (i.e having 100% health) in the range, it open's fire
	 * and returns the confirmation if the intended critter has hit or not
	 */
	public boolean shootCritters(GameMap map, TowerModel tower){
		char[] name_exploded = tower.getMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		boolean isHit=false;


		for(int k=1;k<=tower.getCurrentLevel();k++){

			String xRight = String.valueOf(x+k);
			String xLeft = String.valueOf(x-k);
			String yUp = String.valueOf(y+k);
			String yDown = String.valueOf(y-k);
			try {
				if(map.checkCritterExists(xRight+name_exploded[1])){
					if(strongestCritter==null){
						strongestCritter = map.getCritter(xRight+name_exploded[1]);
					} else if(strongestCritter.getHealth() < map.getCritter(xRight+name_exploded[1]).getHealth()){
						strongestCritter = map.getCritter(xRight+name_exploded[1]);
					}
				} 

				if(map.checkCritterExists(xLeft+name_exploded[1])){
					if(strongestCritter==null){
						strongestCritter = map.getCritter(xLeft+name_exploded[1]);

					} else if(strongestCritter.getHealth() < map.getCritter(xLeft+name_exploded[1]).getHealth()){
						strongestCritter = map.getCritter(xLeft+name_exploded[1]);
					}
				} 

				if(map.checkCritterExists(name_exploded[0]+yUp)){
					if(strongestCritter==null){
						strongestCritter = map.getCritter(name_exploded[0]+yUp);

					} else if(strongestCritter.getHealth() < map.getCritter(name_exploded[0]+yUp).getHealth()){
						strongestCritter = map.getCritter(name_exploded[0]+yUp);
					}
				} 

				if(map.checkCritterExists(name_exploded[0]+yDown)){
					if(strongestCritter==null){
						strongestCritter = map.getCritter(name_exploded[0]+yDown);
					} else if(strongestCritter.getHealth() < map.getCritter(name_exploded[0]+yDown).getHealth()){
						strongestCritter = map.getCritter(name_exploded[0]+yDown);
					}
				}

			} catch (IndexOutOfBoundsException e){
				continue;
			}
		}

		if(strongestCritter!=null){
			strongestCritter.reduceHealth((int)tower.getPowerOfBullets());
			switch(tower.getName()){
			case "Castle Tower":{
				strongestCritter.setBackground("red");
				break;
			}
			case "Imperial Tower":{
				strongestCritter.setBackground("blue");
				break;
			}
			case "Industrial Tower":{
				strongestCritter.setBackground("black");
				break;
			}
			}
			isHit=true;
			strongestCritter=null;
		}
		return isHit;

	}

	/**
	 * return's strategy's name
	 */
	public String GetStrategyName(){
		return StrategyName;
	}
}
