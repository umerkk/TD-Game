package code.game.strategies;

import java.io.Serializable;

import code.game.models.Critter;
import code.game.models.GameMap;
import code.game.models.TowerModel;

/**
 * A Strategy design pattern class implementing Tower's strategy named Nearest to end point.
 * 
 * @author Umer
 * @author Iftikhar
 * @version 1.0.0
 */
public class StrategyNearestToEndPoint implements TowerStrategy, Serializable  {

	public String strategyName="Nearest to End Point";
	public Critter lockedCritter=null;
	private GameMap mapReference=null;
	private final int POINT_ENTRY = 1;
	private final int POINT_EXIT = 9999;

	/**
	 * Shoots critters by deciding, which critter is the nearest to the end point in the range.
	 * if finds two critters in the range, the tower would only shoot the critter nearest to the end point.
	 * @param map for game map
	 * @param tower for tower model
	 * 	 
	 */
	public boolean shootCritters(GameMap map, TowerModel tower){
		this.mapReference = map;
		char[] nameExploded = tower.getMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(nameExploded[0]));
		int y = Integer.parseInt(String.valueOf(nameExploded[1]));
		boolean isHit=false;
		int lastPath=-1;
		String lastIndex="";
		for(int k=1;k<=tower.getRangeOfTower();k++){
			String xRight = String.valueOf(x+k);
			String xLeft = String.valueOf(x-k);
			String yUp = String.valueOf(y+k);
			String yDown = String.valueOf(y-k);

			try {
				if(lastPath<map.getValueFromMap(Integer.parseInt(xRight), y) 
						&& map.getValueFromMap(Integer.parseInt(xRight), y) != POINT_EXIT) {
					lastPath = map.getValueFromMap(Integer.parseInt(xRight), y);
					lastIndex = xRight+nameExploded[1];
					lockedCritter=map.getCritter(xRight+nameExploded[1]);
				}

				if(lastPath<map.getValueFromMap(Integer.parseInt(xLeft), y) 
						&& map.getValueFromMap(Integer.parseInt(xLeft), y) != POINT_EXIT) {
					lastPath = map.getValueFromMap(Integer.parseInt(xLeft), y);
					lastIndex = xLeft+nameExploded[1];
					lockedCritter=map.getCritter(xLeft+nameExploded[1]);
				}

				if(lastPath<map.getValueFromMap(x, Integer.parseInt(yUp)) 
						&& map.getValueFromMap(x, Integer.parseInt(yUp)) != POINT_EXIT) {
					lastPath = map.getValueFromMap(x, Integer.parseInt(yUp));
					lastIndex = nameExploded[0]+yUp;
					lockedCritter=map.getCritter(nameExploded[0]+yUp);
				}
				if(lastPath<map.getValueFromMap(x, Integer.parseInt(yDown))
						&& map.getValueFromMap(x, Integer.parseInt(yDown)) != POINT_EXIT) {
					lastPath = map.getValueFromMap(x, Integer.parseInt(yDown));
					lastIndex = nameExploded[0]+yDown;
					lockedCritter=map.getCritter(nameExploded[0]+yDown);
				}

			} catch (IndexOutOfBoundsException e){
				continue;
			}


		}

		if(!(lastIndex.equalsIgnoreCase(""))) {
			if(map.checkCritterExists(lastIndex)){

				map.getCritter(lastIndex).reduceHealth((int)tower.getPowerOfBullets());
				setBackgroundOfCritter(tower,map.getCritter(lastIndex));
				isHit=true;
			}
		}

		return isHit;
	}

	/**
	 * sets background colour of critter upon hitting
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
	 * @param baseCritter type of critter
	 * @param map for game map
	 * 
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
