package code.game.models;

import java.io.Serializable;

/**
 * a class defining characteristics of a critter of a basic type
 * @author Armaghan
 * @version 1.0.0.0
 */
public class CritterBasic extends Critter implements Serializable {

	/**
	 * sets the characteristics of an basic critter
	 * 
	 * reward the points , player gets by killing the critterhitPoint hit point of the critter
	 * health health of critter
	 * level critter's level speed critter's speed
	 * map game's map on which critters will be drawn
	 * @param map Map object on which the critter will be drawn
	 */
	public CritterBasic(GameMap map) {
		super("Mobster Critter",50,5,50,1,1,map);
	}
}
