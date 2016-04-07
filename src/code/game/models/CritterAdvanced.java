package code.game.models;

import java.io.Serializable;

/**
 * a class defining characteristics of a critter of an advanced type
 * @author Armaghan
 * @version 1.0.0
 */
public class CritterAdvanced extends Critter implements Serializable{
	/**
	 * sets the characteristics of an advanced critter by the following parameters
	 * 
	 * reward the points , player gets by killing the critterhitPoint hit point of the critter
	 * health health of critter
	 * level critter's level speed critter's speed
	 * map game's map on which critters will be drawn
	 * @param map Map object on which the critter will be drawn
	 */
	public CritterAdvanced(GameMap map) {
		super("BigBoss Critter",150,15,150,3,1,map);
	}
}