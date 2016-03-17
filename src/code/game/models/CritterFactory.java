package code.game.models;

/**
 * Critter's Factory pattern which generates different types of critters
 * based on the parameter passed
 * 
 * @author Umer
 * @author Armaghan
 * @author Lokesh
 *
 */
public class CritterFactory {

	/**
	 * empty constructor
	 */
	public CritterFactory(){}

	/**
	 * returns the critter type based on the parameter passed
	 * @param level basic, intermediate, advanced critter level
	 * @param map will be used to draw critter 
	 * @return type of critter class
	 */
	public static Critter getCritter(int level, GameMap map){
		switch(level) {
		case 1: {
			return new BasicCritter(map);
		}
		case 2: {
			return new IntermediateCritter(map);
		}
		case 3: {
			return new AdvanceCritter(map);
		}
		}
		return null;
	}


}
