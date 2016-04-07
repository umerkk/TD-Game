package code.game.models;

import java.io.Serializable;

/**
 * Critter's Factory pattern which generates different types of critters
 * based on the parameter passed
 * 
 * @author Umer
 * @author Lokesh
 * @version 1.0.0.0
 */
public class CritterFactory implements Serializable{

	/**
	 * Method which returns the critter model based on the level.
	 *
	 * @param level the stage level of the critter to be created in map
	 * @param map map model on which critter is to be created.
	 * @return the critter model to be created on the map
	 */
	public static Critter getCritter(int level, GameMap map) {
		switch(level) {
		case 1: {
			return new CritterBasic(map);
		}
		case 2: {
			return new CritterIntermediate(map);
		}
		case 3: {
			return new CritterAdvanced(map);
		}
		}
		return null;
	}
}
