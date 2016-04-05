package code.game.models;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Class responsible of updating all the data being manipulated in the game 
 * e.g 
 * - Player's power
 * - Coins
 * - Balance
 * - Wave no.
 * 
 * @author Umer
 * @author Armaghan
 * @version 1.0.0
 * 
 */
public class GameData extends Observable implements Serializable{

	private  int accountBalance = 20;
	private int playerPower =100;
	private int gameWave=1;
	private String selectedTowerDesc;

	/**
	 * empty constructor
	 */
	public GameData() {
		//this.accountBalance = acctBal;
	}
	
	/**
	 * reset's player's account balance to initial state 20
	 */
	public void resetAccountBalance(){
		this.accountBalance = 20;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * resets player's power back to initial amount 100
	 */
	public void resetPlayerPower(){
		this.playerPower = 100;
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * resets game's wave level
	 */
	public void resetWave(){
		this.gameWave = 1;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * returns player's current power
	 * @return player's current power
	 */
	public int getPlayerPower(){
		return this.playerPower;	
	}
	
	/**
	 * returns game's current wave number
	 * @return current wave number
	 */
	public int getWave(){
		return this.gameWave;	
	}

	/**
	 * sets player's power
	 * @param power player's power
	 */
	public void setPlayerPower(int power){
		this.playerPower = power;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * deducts player's power
	 * @param delta power to deduct
	 */
	public void deductPlayerPower(int delta){
		this.playerPower-=delta;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * returns tower's description, which will later be used to display in tower inspection panel
	 * @return tower's description
	 */
	public String getSelectedTowerDescription(){
		return selectedTowerDesc;	
	}

	/**
	 * modifies selected tower's description as passed in parameter
	 * @param e description of tower
	 */
	public void setSelectedTowerDescription(String e){
		selectedTowerDesc=e;	
		setChanged();
		notifyObservers(this);
	}

	/**
	 * returns player's current account balance
	 * @return player's current account balance
	 */
	public int getAccountBalance(){
		return accountBalance;
	}

	/**
	 * sets player's current account balance
	 * @param money player's current account balance to set
	 */
	public void setAccountBalance(int money){
		this.accountBalance = money;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * increments wave level of game and updates
	 * all the observer's attached
	 */
	public void setWaveIncrement(){
		this.gameWave++;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * increment's players account with coins/money
	 * @param money players coins/money
	 */
	public void addMoneyToAccount(int money){
		this.accountBalance+=money;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * decreases coins/balance from player's account
	 * @param money money to be deducted
	 */
	public void deductMoneyFromAccount(int money){
		this.accountBalance-=money;
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * adds observers's to the observable super class
	 * @param o observer to be added to this class
	 */
	public void addObserver(Observer o){
		super.addObserver(o); //Overiding default behavior of AddObservers to show Balance at initialization.
		setChanged();
		notifyObservers(this);
	}
}
