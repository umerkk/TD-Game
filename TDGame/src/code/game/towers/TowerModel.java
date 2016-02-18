package code.game.towers;

import java.util.Observable;

/**
 * This class defines the necessary properties of the tower and exposes methods to access and modify the tower objects.
 * 
 * @author Umer
 * @author Lokesh
 * @version 1.0.0.0
 */
public class TowerModel extends Observable{

	private String m_name;
	private int m_currlevel;
	private int m_costoftower;
	private int m_upgradecost;
	private int m_refundvalue;
	private int m_rangeinblocks; //Range of the tower per blocks
	private float m_powerofbullet; //Rate of Health to deduct from the target
	private int m_rateoffire; // Interval between each fire
	private int m_health; //Tower health
	
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
						int rangeinblocks, float powerofbullet, int rateoffire)
	{
		m_currlevel = 1;
		m_health = 100;
		m_name = name;
		m_costoftower = costoftower;
		m_upgradecost = upgradecost;
		m_refundvalue = refundvalue;
		m_rangeinblocks = rangeinblocks; 
		m_powerofbullet = powerofbullet;
		m_rateoffire = rateoffire; 
	}
	
	/**
	 * method which returns the details of the tower for displaying on the tower description box.
	 * 
	 * @return all details of the the current tower.
	 */
	public StringBuilder getTowerDetails()
	{
		StringBuilder strBldrObj = new StringBuilder();
		strBldrObj.append("Name : " + m_name);
		strBldrObj.append("\nLevel : " + m_currlevel);
		strBldrObj.append("\nCost of Tower : " + m_costoftower);
		strBldrObj.append("\nUpgrade Cost : " + m_upgradecost);
		strBldrObj.append("\nRefund Value : " + m_refundvalue);
		strBldrObj.append("\nRange : " + m_rangeinblocks);
		strBldrObj.append("\nPower : " + m_powerofbullet);
		strBldrObj.append("\nRate of Fire : " + m_rateoffire);
		strBldrObj.append("\nHealth : " + m_health);
		return strBldrObj;
	}
	/**
	 * method which returns the current level of the tower.
	 * 
	 * @return current level of the tower.
	 */
	public int getCurrentLevel() {return m_currlevel;}
	
	/**
	 * method to update the details of the tower when it is upgraded by the user.
	 */
	public void upgradeCurrentLevel() 
	{
		m_currlevel++;
		m_refundvalue += m_upgradecost;
		m_rangeinblocks++;
		m_health = 100;
		m_powerofbullet *= 1.1;
	}
		
	/**
	 * Method which returns the name of the tower.
	 */
	public String getName() { return m_name;}
	/**
	 * Method returns the cost of buying the tower.
	 */
	public int getCostOfTower() { return m_costoftower; }
	/**
	 * method returns the upgrading cost of the tower.
	 */
	public int getUpgradeCost() { return m_upgradecost; }
	/**
	 * method returns the refund amount while selling the tower.
	 */
	public int getRefundValue() { return m_refundvalue; }
	/**
	 * method returns the range of the tower.
	 */
	public int getRangeOfTower() { return m_rangeinblocks; }
	/**
	 * method returns the power of bullets of the tower.
	 */
	public float getPowerOfBullets() { return m_powerofbullet; }
	/**
	 * method returns the rate at which the tower fires bullets.
	 */
	public int getRateOfFire() { return m_rateoffire; }
		
	/**
	 * method returns the current health of the tower.
	 */
	public int getHealth() { return m_health; }
	/**
	 * method to set the health of the tower. 
	 * @param value new health of the tower.
	 */
	public void setHealth(int value) { m_health = value; setChanged(); notifyObservers(); } 
		
}
