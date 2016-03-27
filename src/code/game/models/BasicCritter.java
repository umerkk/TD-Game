package code.game.models;

/**
 * a class defining characteristics of a critter of a basic type
 * @author Armaghan
 *
 */
public class BasicCritter extends Critter {

	/**
	 * sets the characteristics of an basic critter
	 * 
	 * reward the points , player gets by killing the critterhitPoint hit point of the critter
	 * health health of critter
	 * level critter's level speed critter's speed
	 * map game's map on which critters will be drawn
	 * @param map Map object on which the critter will be drawn
	 */
	public BasicCritter(GameMap map) {
		super(50,5,50,1,1,map);
	}
}
