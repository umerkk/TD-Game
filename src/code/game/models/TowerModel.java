package code.game.models;

import java.io.Serializable;
import java.util.Observable;

import code.game.strategies.TowerStrategy;

/**
 * This class defines the necessary properties of the tower and exposes methods to access and modify the tower objects.
 * 
 * @author Umer
 * @author Lokesh
 * @version 1.0.0.0
 */
public class TowerModel extends Observable implements Serializable{

	private String towerName;
	private int mCurrentLevel;
	private int mcostoftower;
	private int mupgradecost;
	private int mRefundValue;
	private int mrangeinblocks; //Range of the tower per blocks
	private float mpowerofbullet; //Rate of Health to deduct from the target
	private int mrateoffire; // Interval between each fire
	private int mhealth; //Tower health
	private TowerStrategy strategy;
	private GameMap mapRefernce;
	private String myLocationOnMap;

	/**
	 * Constructor for defining an object of the tower model.
	 *  
	 * @param name Name of the tower.
	 * @param costoftower Cost for buying the tower.
	 * @param upgradecost Upgrading cost.
	 * @param refundvalue Refund amount.
	 * @param rangeinblocks Range of the tower in blocks.
	 * @param powerofbullet Power of the bullets.
	 * @param rateoffire Interval at which tower fires the bullet.
	 */
	public TowerModel(String name, int costoftower, int upgradecost, int refundvalue, 
			int rangeinblocks, float powerofbullet, int rateoffire){
		mCurrentLevel = 1;
		mhealth = 100;
		towerName = name;
		mcostoftower = costoftower;
		mupgradecost = upgradecost;
		mRefundValue = refundvalue;
		mrangeinblocks = rangeinblocks; 
		mpowerofbullet = powerofbullet;
		mrateoffire = rateoffire; 
	}

	/**
	 * method which returns the details of the tower for displaying on the tower description box.
	 * 
	 * @return all details of the the current tower.
	 */
	public StringBuilder getTowerDetails(){
		StringBuilder strBldrObj = new StringBuilder();
		strBldrObj.append("Name : " + towerName);
		strBldrObj.append("\nLevel : " + mCurrentLevel);
		strBldrObj.append("\nCost of Tower : " + mcostoftower);
		strBldrObj.append("\nUpgrade Cost : " + mupgradecost);
		strBldrObj.append("\nRefund Value : " + mRefundValue);
		strBldrObj.append("\nRange : " + mrangeinblocks);
		strBldrObj.append("\nPower : " + mpowerofbullet);
		strBldrObj.append("\nRate of Fire : " + mrateoffire);
		strBldrObj.append("\nHealth : " + mhealth);
		if(strategy!=null)
			strBldrObj.append("\nStrategy : " + strategy.getStrategyName());
		else
			strBldrObj.append("\nStrategy : Shown after placement.");
		strBldrObj.append("\nMap Location : " + myLocationOnMap);
		return strBldrObj;
	}

	/**
	 * sets strategy to a tower
	 * - Weakest
	 * - Strongest
	 * - Nearest
	 * 
	 * @param _strategy strategy type
	 * @param map Map object to be updated
	 */
	public void setStrategy(TowerStrategy _strategy, GameMap map){
		this.strategy = _strategy;
		mapRefernce = map;
	}

	/**
	 * set's tower's current location
	 * @param location tower's coordinates
	 */
	public void setMyLocationOnMap(String location){
		this.myLocationOnMap = location;
	}

	/**
	 * return's tower's current location on grid
	 * @return tower's coordinates
	 */
	public String getMyLocationOnMap(){
		return this.myLocationOnMap;
	}

	/**
	 * executes tower's strategy
	 * @return true if the strategy has been executed successfully
	 */
	public boolean executeStrategy(){
		return this.strategy.shootCritters(mapRefernce,this);
	}

	/**
	 * return's selected tower strategy
	 * @return the strategy object
	 */
	public TowerStrategy getStrategy(){
		return this.strategy;
	}

	/**
	 * method which returns the current level of the tower.
	 * @return current level of the tower.
	 */
	public int getCurrentLevel() {
		return mCurrentLevel;
	}

	/**
	 * method to update the details of the tower when it is upgraded by the user.
	 */
	public void upgradeCurrentLevel() {
		mCurrentLevel++;
		mRefundValue += mupgradecost;
		mrangeinblocks++;
		mhealth = 100;
		mpowerofbullet *= 1.1;
	}

	/**
	 * Method which returns the name of the tower.
	 * @return name of tower
	 */
	public String getName() { return towerName;}
	/**
	 * Method returns the cost of buying the tower.
	 * @return cost of tower
	 */
	public int getCostOfTower() { return mcostoftower; }
	/**
	 * method returns the upgrading cost of the tower.
	 * @return upgrade cost
	 */
	public int getUpgradeCost() { return mupgradecost; }
	/**
	 * method returns the refund amount while selling the tower.
	 * @return refund value
	 */
	public int getRefundValue() { return mRefundValue; }
	/**
	 * method returns the range of the tower.
	 * @return range of tower
	 */
	public int getRangeOfTower() { return mrangeinblocks; }
	/**
	 * method returns the power of bullets of the tower.
	 * @return power of bullets
	 */
	public float getPowerOfBullets() { return mpowerofbullet; }
	/**
	 * method returns the rate at which the tower fires bullets.
	 * @return rate of fire
	 */
	public int getRateOfFire() { return mrateoffire; }

	/**
	 * method returns the current health of the tower.
	 * @return helath
	 */
	public int getHealth() { return mhealth; }
	/**
	 * method to set the health of the tower. 
	 * @param value new health of the tower.
	 */
	public void setHealth(int value) { mhealth = value; setChanged(); notifyObservers(); } 

	/**
	 * returns a unique tower ID based on tower's name
	 * @return ID of the tower
	 */
	public int getTowerID() { 
		switch (getName()) {
		case "lblTwr1":
			return 1;
		case "lblTwr2":
			return 2;
		case "lblTwr3":
			return 3;
		default:
			return 1;
		}
	}
}
