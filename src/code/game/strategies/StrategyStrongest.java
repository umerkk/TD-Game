package code.game.strategies;

import java.io.Serializable;

import code.game.models.Critter;
import code.game.models.GameMap;
import code.game.models.TowerModel;

/**
 * A Strategy design pattern class implementing Tower's strategy named Strongest strategy
 * 
 * @author Umer
 * 
 */
public class StrategyStrongest implements TowerStrategy, Serializable{

	public String StrategyName="Strongest First";
	public Critter strongestCritter=null;
	private GameMap mapReference=null;
	
	/**
	 * Shoots critters by deciding, which critter is the strongest in the range.
	 * if finds a strong critter (i.e having 100% health) in the range, it open's fire
	 * and returns the confirmation if the intended critter has hit or not
	 */
	public boolean shootCritters(GameMap map, TowerModel tower){
		this.mapReference=map;
		char[] name_exploded = tower.getMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		boolean isHit=false;
		strongestCritter=null;

		for(int k=1;k<=tower.getRangeOfTower();k++){

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
				strongestCritter.setDamageTime(2);
				strongestCritter.setLastHitBy("Castle Tower");
				break;
			}
			case "Imperial Tower":{
				strongestCritter.setBackground("blue");
				strongestCritter.setDamageTime(2);
				strongestCritter.setLastHitBy("Imperial Tower");
				break;
			}
			case "Industrial Tower":{
				strongestCritter.setBackground("black");
				strongestCritter.setDamageTime(2);
				strongestCritter.setLastHitBy("Industrial Tower");
				hitSplashToCritters(strongestCritter, mapReference);
				
				break;
			}
			}
			isHit=true;
			//strongestCritter=null;
		}
		return isHit;

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
