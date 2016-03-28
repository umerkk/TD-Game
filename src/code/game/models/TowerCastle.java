package code.game.models;

/**
 * Castle tower extends the tower model class and is a a type of tower available in the game
 * 
 * @author Umer
 *
 */
public class TowerCastle extends TowerModel {
	
	/**
	 * sets the characteristics of an advanced critter
	 * in the following order
	 * 
	 * name Name of the tower.
	 * costoftower Cost for buying the tower.
	 * upgradecost Upgrading cost.
	 * refundvalue Refund amount.
	 * rangeinblocks Range of the tower in blocks.
	 * powerofbullet Power of the bullets.
	 * rateoffire Interval at which tower fires the bullet.
	 */
	public TowerCastle() {
		super("Castle Tower", 10, 5, 5, 1, 20, 10);
	}
}
