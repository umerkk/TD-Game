package code.game.Models;

public class CritterFactory {

	public CritterFactory(){}


	public static Critter getCritter(int level)
	{
		switch(level)
		{
		case 1:
		{
			return new BasicCritter();
		}
		case 2:
		{
			return new IntermediateCritter();
		}
		case 3:
		{
			return new AdvanceCritter();
		}
		}
		return null;
	}


}
