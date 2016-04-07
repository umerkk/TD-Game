package code.game.models;

import java.io.Serializable;

/**
 * A dummy MapModel containing only attributes which will be used to hold and store 
 * map statistics and details, hence extending MapLogger
 * @author Armaghan
 * @version 1.0.0
 */
public class MapModel extends MapLogger implements Serializable{
	
	/**
	 * a unique ID used for serialization when reading writing a class object
	 */
	private static final long SERIAL_VERSION_UID = 8899;
	private int[][] mapArray;
	
	/**
	 * returns actual map grid array
	 * @return 2D array containing map's grid points
	 */
	public int[][] getMapArray() {
		return mapArray;
	}
	
	/**
	 * sets map's array containing grid info 
	 * @param mapArray 2-d array containing map's grid points
	 */
	public void setMapArray(int[][] mapArray) {
		this.mapArray = mapArray;
	}
}
