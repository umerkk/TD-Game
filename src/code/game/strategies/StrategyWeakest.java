package code.game.strategies;

import java.io.Serializable;

import code.game.models.Critter;
import code.game.models.GameMap;
import code.game.models.TowerModel;

/**
 * A Strategy design pattern class implementing Tower's strategy named Weakest strategy
 * 
 * @author Umer
 * @author Lokesh
 * @version 1.0.0
 */
public class StrategyWeakest implements TowerStrategy, Serializable{

	public String strategyName="Weakest First";
	public Critter weakestCritter=null;
	private GameMap mapReference=null;
	
	/**
	 * Shoots critters by deciding, which critter is the weakest in the range.
	 * if weak in the range, it open's fire and returns the confirmation if the intended critter has hit or not
	 * @param map for game map
	 * @param tower for type of tower model
	 */
	public boolean shootCritters(GameMap map, TowerModel tower){
		this.mapReference = map;
		char[] nameExploded = tower.getMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(nameExploded[0]));
		int y = Integer.parseInt(String.valueOf(nameExploded[1]));
		boolean isHit=false;
		weakestCritter=null;
		
		for(int k=1;k<=tower.getRangeOfTower();k++){
			try {
				String xRight = String.valueOf(x+k);
				String xLeft = String.valueOf(x-k);
				String yUp = String.valueOf(y+k);
				String yDown = String.valueOf(y-k);

				if(map.checkCritterExists(xRight+nameExploded[1])){
					if(weakestCritter==null){
						weakestCritter = map.getCritter(xRight+nameExploded[1]);
					} else if(weakestCritter.getHealth() > map.getCritter(xRight+nameExploded[1]).getHealth())
					{
						weakestCritter = map.getCritter(xRight+nameExploded[1]);
					}
				} 

				if(map.checkCritterExists(xLeft+nameExploded[1])){
					if(weakestCritter==null){
						weakestCritter = map.getCritter(xLeft+nameExploded[1]);

					} else if(weakestCritter.getHealth() > map.getCritter(xLeft+nameExploded[1]).getHealth()){
						weakestCritter = map.getCritter(xLeft+nameExploded[1]);
					}
				} 

				if(map.checkCritterExists(nameExploded[0]+yUp)){
					if(weakestCritter==null){
						weakestCritter = map.getCritter(nameExploded[0]+yUp);

					} else if(weakestCritter.getHealth() > map.getCritter(nameExploded[0]+yUp).getHealth()){
						weakestCritter = map.getCritter(nameExploded[0]+yUp);
					}
				} 

				if(map.checkCritterExists(nameExploded[0]+yDown)){
					if(weakestCritter==null){
						weakestCritter = map.getCritter(nameExploded[0]+yDown);
					} else if(weakestCritter.getHealth() > map.getCritter(nameExploded[0]+yDown).getHealth()){
						weakestCritter = map.getCritter(nameExploded[0]+yDown);
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
				weakestCritter.setDamageTime(2);
				weakestCritter.setLastHitBy("Castle Tower");
				break;
			}
			case "Imperial Tower":{
				weakestCritter.setBackground("blue");
				weakestCritter.setDamageTime(2);
				weakestCritter.setLastHitBy("Imperial Tower");
				break;
			}
			case "Industrial Tower":{
				weakestCritter.setBackground("black");
				weakestCritter.setDamageTime(2);
				weakestCritter.setLastHitBy("Industrial Tower");
				hitSplashToCritters(weakestCritter, mapReference);
				break;
			}
			}
			isHit=true;
			//weakestCritter=null;
		}
		return isHit;
	}

	/**
	 * Shoot the nearby critters through Splash effect of Industrial tower.
	 * It finds the critter in adjacent position and deduct 10 health from those critters,
	 * making a soft damage to them.
	 * @param baseCritter type of critter
	 * @param map for game map
	 */
	private void hitSplashToCritters(Critter baseCritter, GameMap map){
		char[] nameExploded = baseCritter.getMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(nameExploded[0]));
		int y = Integer.parseInt(String.valueOf(nameExploded[1]));
		boolean isHit=false;
		
		
		String xRight = String.valueOf(x+2);
		String xLeft = String.valueOf(x-2);
		String yUp = String.valueOf(y+2);
		String yDown = String.valueOf(y-2);
		
		try {
			if(map.checkCritterExists(xRight+nameExploded[1])){
				
					map.getCritter(xRight+nameExploded[1]).reduceHealth(10);
					map.getCritter(xRight+nameExploded[1]).setBackground("black");
				
			} 

			if(map.checkCritterExists(xLeft+nameExploded[1])){
				
					map.getCritter(xLeft+nameExploded[1]).reduceHealth(10);
					map.getCritter(xLeft+nameExploded[1]).setBackground("black");
				
			} 

			if(map.checkCritterExists(nameExploded[0]+yUp)){
				
				map.getCritter(nameExploded[0]+yUp).reduceHealth(10);
				map.getCritter(nameExploded[0]+yUp).setBackground("black");
				
			} 

			if(map.checkCritterExists(nameExploded[0]+yDown)){
			
				map.getCritter(nameExploded[0]+yDown).reduceHealth(10);
				map.getCritter(nameExploded[0]+yDown).setBackground("black");
				
			
			}

		} catch (IndexOutOfBoundsException e){
			//continue;
		}
		
	}
	
	/**
	 * @return return's strategy's name
	 */
	public String getStrategyName(){
		return strategyName;
	}
}
