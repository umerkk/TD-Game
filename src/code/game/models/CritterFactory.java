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
	 * empty constructor
	 */
	public CritterFactory() { }

	/**
	 * returns the critter type based on the parameter passed
	 * @param level basic, intermediate, advanced critter level
	 * @param map will be used to draw critter
	 *  
	 * @return type of critter class
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
