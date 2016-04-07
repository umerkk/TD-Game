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

public class TowerModel extends Observable implements Serializable {

	private String towerName;
	private int mCurrentLevel;
	private int mCostOfTower;
	private int mUpgradeCost;
	private int mRefundValue;
	private int mRangeInBlocks; //Range of the tower per blocks
	private float mPowerOfBullet; //Rate of Health to deduct from the target
	private int mRateOfFire; // Interval between each fire
	private int mHealth; //Tower health
	private TowerStrategy strategy;
	private GameMap mapRefernce;
	private String myLocationOnMap;

	/**
	 * Constructor for defining an object of the tower model.
	 *  
	 * @param name Name of the tower.
	 * @param costOfTower Cost for buying the tower.
	 * @param upgradeCost Upgrading cost.
	 * @param refundValue Refund amount.
	 * @param rangeInBlocks Range of the tower in blocks.
	 * @param powerOfBullet Power of the bullets.
	 * @param rateOfFire Interval at which tower fires the bullet.
	 */
	public TowerModel(String name, int costOfTower, int upgradeCost, int refundValue, 
			int rangeInBlocks, float powerOfBullet, int rateOfFire) {
		mCurrentLevel = 1;
		mHealth = 100;
		towerName = name;
		mCostOfTower = costOfTower;
		mUpgradeCost = upgradeCost;
		mRefundValue = refundValue;
		mRangeInBlocks = rangeInBlocks; 
		mPowerOfBullet = powerOfBullet;
		mRateOfFire = rateOfFire; 
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
		strBldrObj.append("\nCost of Tower : " + mCostOfTower);
		strBldrObj.append("\nUpgrade Cost : " + mUpgradeCost);
		strBldrObj.append("\nRefund Value : " + mRefundValue);
		strBldrObj.append("\nRange : " + mRangeInBlocks);
		strBldrObj.append("\nPower : " + mPowerOfBullet);
		strBldrObj.append("\nRate of Fire : " + mRateOfFire);
		strBldrObj.append("\nHealth : " + mHealth);
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
	 * @param tmpStrategy strategy type
	 * @param map Map object to be updated
	 */
	public void setStrategy(TowerStrategy tmpStrategy, GameMap map){
		this.strategy = tmpStrategy;
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
		mRefundValue += mUpgradeCost;
		mRangeInBlocks++;
		mHealth = 100;
		mPowerOfBullet *= 1.1;
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
	public int getCostOfTower() { return mCostOfTower; }
	/**
	 * method returns the upgrading cost of the tower.
	 * @return upgrade cost
	 */
	public int getUpgradeCost() { return mUpgradeCost; }
	/**
	 * method returns the refund amount while selling the tower.
	 * @return refund value
	 */
	public int getRefundValue() { return mRefundValue; }
	/**
	 * method returns the range of the tower.
	 * @return range of tower
	 */
	public int getRangeOfTower() { return mRangeInBlocks; }
	/**
	 * method returns the power of bullets of the tower.
	 * @return power of bullets
	 */
	public float getPowerOfBullets() { return mPowerOfBullet; }
	/**
	 * method returns the rate at which the tower fires bullets.
	 * @return rate of fire
	 */
	public int getRateOfFire() { return mRateOfFire; }

	/**
	 * method returns the current health of the tower.
	 * @return helath
	 */
	public int getHealth() { return mHealth; }
	/**
	 * method to set the health of the tower. 
	 * @param value new health of the tower.
	 */
	public void setHealth(int value) { 
		mHealth = value; 
		setChanged(); 
		notifyObservers(); 
	} 

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
