package code.game.models;

/**
 * Class containing the advanced critter setter
 * @author Armaghan
 *
 */
public class AdvanceCritter extends Critter {

	/**
	 * sets the characteristics of an advanced critter by the following parameters
	 * 
	 * reward the points , player gets by killing the critterhitPoint hit point of the critter
	 * health health of critter
	 * level critter's level speed critter's speed
	 * map game's map on which critters will be drawn
	 * @param map Map object on which the critter will be drawn
	 */
	public AdvanceCritter(GameMap map) {
		super(150,15,150,3,3,map);
	}
}
