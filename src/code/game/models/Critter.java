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
	private int hit_point;
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
	public Critter(String name, int reward,int hitPoint, int health, int level, int speed,GameMap map) {
		this.name = name;
		this.reward = reward;
		this.hit_point = hitPoint;
		this.health = health;
		this.level= level;
		this.speed = speed;
		this.mapReference = map;
		this.constSpeed = speed;
	}

	// add the critter details
	public StringBuilder getCritterDetails(){
		StringBuilder strBldrObj = new StringBuilder();
		strBldrObj.append("Name : "+name);
		strBldrObj.append("\nLevel : " + level);
		strBldrObj.append("\nHealth : " + health);
		strBldrObj.append("\nKill Reward : " + reward);
		strBldrObj.append("\nHit Point : " + hit_point);
		strBldrObj.append("\nSpeed : " + speed);
		strBldrObj.append("\nCurrent Location : " + myLocationOnMap);
		strBldrObj.append("\nLast Hit By: " + lastHitBy);
		strBldrObj.append("\nDamage Time : " + damageTime);

		return strBldrObj;
	}
	
	public void resetSpeed()
	{
		this.speed = this.constSpeed;
	}
	public void reduceSpeed(int amount)
	{
		this.speed-=amount;
	}
	public void setLastHitBy(String tower)
	{
		this.lastHitBy = tower;
	}
	public String getLastHitBy()
	{
		return this.lastHitBy;	
	}
	// set the time for critters' damage
	public void setDamageTime(int time)
	{
		this.damageTime = time;
	}
	
	public int getDamageTime()
	{
		return this.damageTime;
	}
	/**
	 * Critter's current location on the grid
	 * @param location Critter's current location
	 */
	public void setMyLocationOnMap(String location){
		this.myLocationOnMap = location;
	}

	/**
	 * Returns the current location of the critter on the grid.
	 * @return Critter's current location
	 */
	public String getMyLocationOnMap(){
		return this.myLocationOnMap;
	}

	/**
	 * Sets critter's background image name
	 * @param bg critter's background
	 */
	public void setBackground(String bg){
		this.background = bg;
	}

	/**
	 * Gets critter's background image name
	 * @return critter's background
	 */
	public String getBackground(){
		return this.background;
	}


	/**
	 * method to set reward of destroying critter
	 * @param reward reward earned for destroying critter
	 */
	public void setReward(int reward){
		this.reward = reward;
	}

	/**
	 * Sets critter's hit point
	 * @param hitPoint hit point to be set 
	 */
	public void setHitPoint(int hitPoint){
		this.hit_point = hitPoint;
	}

	/**
	 * Sets critter's health
	 * @param health critter's health
	 */
	public void setHealth(int health){
		this.health = health;
	}


	/**
	 * Sets critter's level 
	 * - Advanced
	 * - Basic
	 * @param level critter's level
	 */
	public void setLevel(int level){
		this.level = level;
	}

	/**
	 * Sets critters speed
	 * @param speed critter's speed
	 */
	public void setSepeed(int speed){
		this.speed = speed;
	}

	/**
	 * Decrements critter's health as the tower's shoot at them
	 * @param hitValue critter's hit value
	 */
	public void reduceHealth(int hitValue){
		this.health -= hitValue;
	}

	/**
	 * Returns the reward the user gets by killing the critter
	 * @return player's reward
	 */
	public int getReward(){
		return this.reward;
	}

	/**
	 * Returns critter's hit point
	 * @return critter's hit point
	 */
	public int getHitPoint(){
		return this.hit_point;
	}

	/**
	 * Returns critter's health
	 * @return critter's health
	 */
	public int getHealth(){
		return this.health;
	}

	/**
	 * Returns critter's level
	 * - Advanced
	 * - Basic
	 * @return critter's level
	 */
	public int getLevel(){
		return this.level;
	}

	/**
	 * Returns critter's speed
	 * @return critter's speed
	 */
	public int getSpeed(){
		return this.speed;
	}
}
