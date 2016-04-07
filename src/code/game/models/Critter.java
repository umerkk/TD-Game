package code.game.models;

import java.io.Serializable;

/**
 * Main model class of critter getting and setting all the characteristics of critter
 * e.g 
 * - Health
 * - Speed
 * - Hit points
 * - Level
 * - Player's reward
 * @author Armaghan
 * @author Umer
 *
 */
public abstract class Critter implements Serializable {

	private int reward;
	private String name;
	private int hitPoint;
	private int health;
	private int level;
	private int speed;
	private final int constSpeed;
	private GameMap mapReference;
	private String myLocationOnMap;
	private String background="None";
	private int damageTime=0;
	private String lastHitBy;


	/**
	 * Constructor of critter model, sets the following parameters
	 * @param name for Critter
	 * @param reward the points , player gets by killing the critter
	 * @param hitPoint hit point of the critter
	 * @param health health of critter
	 * @param level critter's level 
	 * @param speed critter's speed
	 * @param map game's map on which critters will be drawn
	 */
	public Critter(String name, int reward, int hitPoint, int health, int level, int speed,GameMap map) {
		this.name = name;
		this.reward = reward;
		this.hitPoint = hitPoint;
		this.health = health;
		this.level= level;
		this.speed = speed;
		this.mapReference = map;
		this.constSpeed = speed;
	}

	/**
	 * Method to get the complete critter details of the current critter object
	 * @return details of the critter object
	 */
	public StringBuilder getCritterDetails(){
		StringBuilder strBldrObj = new StringBuilder();
		strBldrObj.append("Name : "+name);
		strBldrObj.append("\nLevel : " + level);
		strBldrObj.append("\nHealth : " + health);
		strBldrObj.append("\nKill Reward : " + reward);
		strBldrObj.append("\nHit Point : " + hitPoint);
		strBldrObj.append("\nSpeed : " + speed);
		strBldrObj.append("\nCurrent Location : " + myLocationOnMap);
		strBldrObj.append("\nLast Hit By: " + lastHitBy);
		strBldrObj.append("\nDamage Time : " + damageTime);

		return strBldrObj;
	}

	/**
	 * To reset the critters speed to normal
	 */
	public void resetSpeed() {
		this.speed = this.constSpeed;
	}

	/**
	 * To reduce the critter's speed by a specified speed.
	 * @param amount the speed that needs to be reduced from the original speed.
	 */
	public void reduceSpeed(int amount) {
		this.speed-=amount;
	}

	/**
	 * Method to set the last hit by tower of the critter object.
	 * @param tower last tower to hit the critter.
	 */
	public void setLastHitBy(String tower) {
		this.lastHitBy = tower;
	}

	/**
	 * Method to get the last tower which hit the critter.
	 * @return tower which last hit the tower.
	 */
	public String getLastHitBy() {
		return this.lastHitBy;	
	}

	/**
	 * Method to set the damage time of the critter hit by a tower.
	 * @param damage time for the critter
	 */
	public void setDamageTime(int time) {
		this.damageTime = time;
	}

	/**
	 * Method to get the damage time of the critter.
	 * @return damage time of the critter
	 */
	public int getDamageTime() {
		return this.damageTime;
	}

	/**
	 * Method to set the critter's current location on the map.
	 * @param location Critter's current location
	 */
	public void setMyLocationOnMap(String location) {
		this.myLocationOnMap = location;
	}

	/**
	 * Method to get the current location of the critter on the map.
	 * @return Critter's current location
	 */
	public String getMyLocationOnMap() {
		return this.myLocationOnMap;
	}

	/**
	 * Method to set the current background of the critter.
	 * @param bg critter's background to be set
	 */
	public void setBackground(String bg) {
		this.background = bg;
	}

	/**
	 * Method to get the current background of th critter 
	 * @return critter's background
	 */
	public String getBackground() {
		return this.background;
	}

	/**
	 * Method to set the reward to be awarded when the critter is destroyed.
	 * @param reward reward to be set
	 */
	public void setReward(int reward) {
		this.reward = reward;
	}

	/**
	 * Method to set the critter's hit point
	 * @param hitPoint hit point to be set 
	 */
	public void setHitPoint(int hitPoint) {
		this.hitPoint = hitPoint;
	}

	/**
	 * Method to set the current health of the critter
	 * @param health critter's health to be set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Sets the critter's level 
	 * - Advanced
	 * - Basic
	 * @param level critter's level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Sets critters current speed
	 * @param speed critter's speed
	 */
	public void setSepeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Decrements critter's health as the tower's shoot at them
	 * @param hitValue critter's hit value
	 */
	public void reduceHealth(int hitValue) {
		this.health -= hitValue;
	}

	/**
	 * Returns the reward the user gets by killing the critter
	 * @return player's reward
	 */
	public int getReward() {
		return this.reward;
	}

	/**
	 * Returns critter's hit point
	 * @return critter's hit point
	 */
	public int getHitPoint() {
		return this.hitPoint;
	}

	/**
	 * Returns critter's health
	 * @return critter's health
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Returns critter's level
	 * - Advanced
	 * - Basic
	 * @return critter's level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * Returns critter's speed
	 * @return critter's speed
	 */
	public int getSpeed() {
		return this.speed;
	}
}
