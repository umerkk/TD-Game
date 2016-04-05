package code.game.strategies;

import java.io.Serializable;

import code.game.models.Critter;
import code.game.models.GameMap;
import code.game.models.TowerModel;

/**
 * A Strategy design pattern class implementing Tower's strategy named Nearest strategy
 * 
 * @author Umer
 * @author Iftikhar
 * @version 1.0.0
 */
public class StrategyNearest  implements TowerStrategy, Serializable {

	public String StrategyName="Nearest First";
	public Critter lockedCritter=null;
	private GameMap mapReference=null;

	/**
	 * Shoots critters by deciding, which critter is the nearest in the range.
	 * if finds two critters in the range, the tower would only shoot the critter 
	 * which is the nearest in the range
	 * and returns the confirmation if the intended critter has hit or not
	 */
	public boolean shootCritters(GameMap map, TowerModel tower){
		this.mapReference = map;
		char[] name_exploded = tower.getMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		Boolean isIgnore=false;
		boolean isHit=false;
		for(int k=1;k<=tower.getRangeOfTower();k++){
			String xRight = String.valueOf(x+k);
			String xLeft = String.valueOf(x-k);
			String yUp = String.valueOf(y+k);
			String yDown = String.valueOf(y-k);
			if(!isIgnore){
				try {
					if(map.checkCritterExists(xRight+name_exploded[1])){
						map.getCritter(xRight+name_exploded[1]).reduceHealth((int)tower.getPowerOfBullets());
						setBackgroundOfCritter(tower,map.getCritter(xRight+name_exploded[1]));
						lockedCritter=map.getCritter(xRight+name_exploded[1]);
						isIgnore = true;
						isHit=true;
					} else if(map.checkCritterExists(xLeft+name_exploded[1])){
						map.getCritter(xLeft+name_exploded[1]).reduceHealth((int)tower.getPowerOfBullets());
						setBackgroundOfCritter(tower,map.getCritter(xLeft+name_exploded[1]));
						lockedCritter=map.getCritter(xLeft+name_exploded[1]);
						isIgnore = true;
						isHit=true;
					} else if(map.checkCritterExists(name_exploded[0]+yUp)){
						map.getCritter(name_exploded[0]+yUp).reduceHealth((int)tower.getPowerOfBullets());
						setBackgroundOfCritter(tower,map.getCritter(name_exploded[0]+yUp));
						lockedCritter=map.getCritter(name_exploded[0]+yUp);
						isIgnore = true;
						isHit=true;

					} else if(map.checkCritterExists(name_exploded[0]+yDown)){
						map.getCritter(name_exploded[0]+yDown).reduceHealth((int)tower.getPowerOfBullets());
						setBackgroundOfCritter(tower,map.getCritter(name_exploded[0]+yDown));
						lockedCritter=map.getCritter(name_exploded[0]+yDown);
						isIgnore = true;
						isHit=true;

					}
				} catch (IndexOutOfBoundsException e){
					continue;
				}
			}
		}
		return isHit;
	}

	/**
	 * sets background color of critter upon hitting
	 * @param tower type of tower
	 * @param critter the critter to be updated
	 */
	private void setBackgroundOfCritter(TowerModel tower, Critter critter){
		switch(tower.getName()){
		case "Castle Tower":{
			critter.setBackground("red");
			critter.setDamageTime(2);
			critter.setLastHitBy("Castle Tower");
			break;
		}
		case "Imperial Tower":{
			critter.setBackground("blue");
			critter.setDamageTime(2);
			critter.setLastHitBy("Imperial Tower");
			break;
		}
		case "Industrial Tower":{
			critter.setBackground("black");
			critter.setDamageTime(2);
			critter.setLastHitBy("Industrial Tower");
			hitSplashToCritters(critter, mapReference);
			break;
		}
		}
	}

	/**
	 * Shoot the nearby critters through Splash effect of Industrial tower.
	 * It finds the critter in adjacent position and deduct 10 health from those critters,
	 * making a soft damage to them.
	 */
	private void hitSplashToCritters(Critter baseCritter, GameMap map){
		char[] name_exploded = baseCritter.getMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		boolean isHit=false;
		
		
		String xRight = String.valueOf(x+2);
		String xLeft = String.valueOf(x-2);
		String yUp = String.valueOf(y+2);
		String yDown = String.valueOf(y-2);
		
		try {
			if(map.checkCritterExists(xRight+name_exploded[1])){
				
					map.getCritter(xRight+name_exploded[1]).reduceHealth(10);
					map.getCritter(xRight+name_exploded[1]).setBackground("black");
				
			} 

			if(map.checkCritterExists(xLeft+name_exploded[1])){
				
					map.getCritter(xLeft+name_exploded[1]).reduceHealth(10);
					map.getCritter(xLeft+name_exploded[1]).setBackground("black");
				
			} 

			if(map.checkCritterExists(name_exploded[0]+yUp)){
				
				map.getCritter(name_exploded[0]+yUp).reduceHealth(10);
				map.getCritter(name_exploded[0]+yUp).setBackground("black");
				
			} 

			if(map.checkCritterExists(name_exploded[0]+yDown)){
			
				map.getCritter(name_exploded[0]+yDown).reduceHealth(10);
				map.getCritter(name_exploded[0]+yDown).setBackground("black");
				
			
			}

		} catch (IndexOutOfBoundsException e){
			//continue;
		}
		
	}
	
	
	/**
	 * return's strategy's name
	 */
	public String getStrategyName(){
		return StrategyName;
	}
}


