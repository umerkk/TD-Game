package code.game.Models;

import java.util.Observable;
import java.util.Observer;

public class GameData  extends Observable{

	private  int accountBalance;
	private String selectedTowerDesc;

	//==================================================
	public GameData(int acctBal)
	{
		this.accountBalance = acctBal;
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
