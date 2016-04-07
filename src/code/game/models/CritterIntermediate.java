package code.game.models;

import java.io.Serializable;

/**
 * a class defining characteristics of a critter of an intermediate type
 * @author Armaghan
 * @author Umer
 * @version 1.0.0.0
 */
public class CritterIntermediate extends Critter implements Serializable{

	/**
	 * sets the characteristics of an intermediate critter by the following parameters
	 * 
	 * reward the points , player gets by killing the critterhitPoint hit point of the critter
	 * health health of critter
	 * level critter's level speed critter's speed
	 * map game's map on which critters will be drawn
	 * 
	 * @param map Map object on which the critter will be drawn
	 */
	public CritterIntermediate(GameMap map){
		super("Sproadic Killer",100,10,100,2,2,map);
	}
}
