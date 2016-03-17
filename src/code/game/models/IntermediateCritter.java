package code.game.models;

public class IntermediateCritter extends Critter {

	/**
	 * sets the characteristics of an intermediate critter by the following parameters
	 * 
	 * reward the points , player gets by killing the critterhitPoint hit point of the critter
	 * health health of critter
	 * level critter's level speed critter's speed
	 * map game's map on which critters will be drawn
	 * @param map Map object on which the critter will be drawn
	 */
	public IntermediateCritter(GameMap map){
		super(100,10,100,2,2,map);
	}
}