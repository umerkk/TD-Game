package code.game.Models;

public abstract class Critter {

	private int reward;
	private int hit_point;
	private int health;
	private int level;
	private int speed;
	
	
	public Critter()
	{
		
	}
	
	public Critter(int reward,int hitPoint, int health, int level, int speed)
	{
		this.reward = reward;
		this.hit_point = hitPoint;
		this.health = health;
		this.level= level;
		this.speed = speed;
	}
	
	
	public void SetReward(int reward)
	{
		this.reward = reward;
	}
	
	public void SetHitPoint(int hitPoint)
	{
		this.hit_point = hitPoint;
	}
	
	public void SetHealth(int health)
	{
		this.health = health;
	}
	
	
	public void SetLevel(int level)
	{
		this.level = level;
	}
	
	public void SetSepeed(int speed)
	{
		this.speed = speed;
	}
	
	public void ReduceHealth(int hitValue)
	{
		this.health -= hitValue;
	}
	
	public int GetReward()
	{
		return this.reward;
	}
	
	public int GetHitPoint()
	{
		return this.hit_point;
	}
	
	public int GetHealth()
	{
		return this.health;
	}
	
	public int GetLevel()
	{
		return this.level;
	}
	
	public int GetSpeed()
	{
		return this.speed;
	}
}
