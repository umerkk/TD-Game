package code.game.models;

public class CritterFactory {

	public CritterFactory(){}


	public static Critter getCritter(int level,GameMap map)
	{
		switch(level)
		{
		case 1:
		{
			return new BasicCritter(map);
		}
		case 2:
		{
			return new IntermediateCritter(map);
		}
		case 3:
		{
			return new AdvanceCritter(map);
		}
		}
		return null;
	}


}
