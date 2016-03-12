package code.game.Controllers;

public class Controller {

	private static GameController instance;

	private Controller()
	{
		//No instance
	}


	public GameController getGameControllerInstance()
	{
		if(instance==null)
		{
			instance = new GameController();
			return instance;
		} else 
		{
			return instance;
		}
	}


}
