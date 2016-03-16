package code.game.Controllers;

public class Controller {

	private static SingleGameController instance;

	private Controller()
	{
		//No instance
	}


	public static SingleGameController getGameControllerInstance()
	{
		if(instance==null)
		{
			instance = new SingleGameController();
			return instance;
		} else 
		{
			return instance;
		}
	}


}
