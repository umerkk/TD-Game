package code.game.models;

/**
 * Imperial tower extends the tower model class and is a a type of tower available in the game
 * 
 * @author Umer
 *
 */
public class ImperialTower extends TowerModel {

	/**
	 * name Name of the tower.
	 * costoftower Cost for buying the tower.
	 * upgradecost Upgrading cost.
	 * refundvalue Refund amount.
	 * rangeinblocks Range of the tower in blocks.
	 * powerofbullet Power of the bullets.
	 * rateoffire Interval at which tower fires the bullet.
	 */
	public ImperialTower(){
		super("Imperial Tower", 20, 10, 10, 2, 40, 10);
	}
	
}
