package code.game.models;

/**
 * Industrial Tower extends the tower model class and is a a type of tower available in the game
 * 
 * @author Umer
 */
public class IndustrialTower extends TowerModel {

	/**
	 * name Name of the tower.
	 * costoftower Cost for buying the tower.
	 * upgradecost Upgrading cost.
	 * refundvalue Refund amount.
	 * rangeinblocks Range of the tower in blocks.
	 * powerofbullet Power of the bullets.
	 * rateoffire Interval at which tower fires the bullet.
	 */
	public IndustrialTower() {
		super("Industrial Tower", 30, 15, 15, 3, 60, 10);
	}
}
