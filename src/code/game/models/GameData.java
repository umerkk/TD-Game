package code.game.models;

import java.util.Observable;
import java.util.Observer;

public class GameData  extends Observable{

	private  int accountBalance = 20;
	private int playerPower =100;
	private String selectedTowerDesc;

	//==================================================
	public GameData()
	{
		//this.accountBalance = acctBal;
	}
	public void ResetAccountBalance()
	{
		this.accountBalance = 20;
		setChanged();
		notifyObservers(this);
	}
	
	public void ResetPlayerPower()
	{
		this.playerPower = 100;
		setChanged();
		notifyObservers(this);
	}

	public int GetPlayerPower()
	{
		return this.playerPower;	
	}
	
	public void SetPlayerPower(int power)
	{
		this.playerPower = power;
		setChanged();
		notifyObservers(this);
	}
	
	public void DeductPlayerPower(int delta)
	{
		this.playerPower-=delta;
		setChanged();
		notifyObservers(this);
	}
	
	public String GetSelectedTowerDescription()
	{
		return selectedTowerDesc;	
	}
	
	public void SetSelectedTowerDescription(String e)
	{
		selectedTowerDesc=e;	
		setChanged();
		notifyObservers(this);
	}
	
	public int GetAccountBalance()
	{
		return accountBalance;
	}

	public void SetAccountBalance(int money)
	{
		this.accountBalance = money;
		setChanged();
		notifyObservers(this);
	}

	public void AddMoneyToAccount(int money)
	{
		this.accountBalance+=money;
		setChanged();
		notifyObservers(this);
	}

	public void DeductMoneyFromAccount(int money)
	{
		this.accountBalance-=money;
		setChanged();
		notifyObservers(this);
		
	}
	//Overiding default behavior of AddObservers to show Balance at initialization.
	public void addObserver(Observer o)
	{
		super.addObserver(o);
		setChanged();
		notifyObservers(this);
	}
	
	
}
