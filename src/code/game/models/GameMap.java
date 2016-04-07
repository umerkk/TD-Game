package code.game.models;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;;

/**
 * the main class of game's map, performing essential tasks about map
 * acts as an observable
 * @author Umer
 * @author Lokesh
 * @version 1.0.0
 */
public class GameMap extends Observable implements Serializable {

	private String mapName;
	private int[][] mapArray;
	private int[][] critterPath;
	private HashMap<String,TowerModel> towerCollection = new HashMap<String,TowerModel>();
	private ConcurrentHashMap<String,Critter> critterCollection = new ConcurrentHashMap<String,Critter>();
	private int arrayRow;
	private int arrayCol;


	/**
	 * acts as class' constructor having 2 parameters
	 * @param tmpMapName map's name in string
	 * @param tmpMapArray containing rows and columns in an array
	 */
	public void initialize(String tmpMapName, int[][] tmpMapArray) {
		this.mapName = tmpMapName;
		this.mapArray = tmpMapArray;
		this.arrayRow = this.mapArray.length;
		this.arrayCol = this.mapArray[0].length;
	}

	/**
	 * acts as class' constructor having 3 parameters
	 * @param tmpMapName map's name in string
	 * @param row no. of rows (4-9)
	 * @param col no. of columns (4-9)
	 */
	public void initialize(String tmpMapName, int row, int col){
		this.mapName = tmpMapName;
		this.mapArray = new int[row][col];
		this.critterPath = new int[row][col];
		this.arrayRow = this.mapArray.length;
		this.arrayCol = this.mapArray[0].length;
	}
	/**
	 * 
	 * @param x value1
	 * @param y value2
	 * @return array of map
	 */
	public int getValueFromMap(int x, int y)
	{
		return mapArray[x][y];
	}

	/**
	 * returns map's name
	 * @return map name
	 */
	public String getName(){
		return this.mapName;
	}
	
	/**
	 * returns only row's array 
	 * @return row's array 
	 */
	public int getArrayRow(){
		return this.arrayRow;
	}

	/**
	 * returns only column's array 
	 * @return column's array 
	 */
	public int getArrayCol(){
		return this.arrayCol;
	}

	/**
	 * returns grid of map
	 * @return grid array of map
	 */
	public int[][] getMapArray(){
		return this.mapArray;
	}

	/**
	 * returns tower of the provided coordinates
	 * @param location tower's coordinates
	 * @return tower object
	 */
	public TowerModel getTower(String location){
		if(towerCollection.containsKey(location)){
			return towerCollection.get(location);
		} else {
			return null;
		}
	}

	/**
	 * activates all the available towers to shoot at critters
	 * @return total hit count of towers
	 */
	public int towerToShoot(){
		int totalHits=0;
		for (Map.Entry<String, TowerModel> entry : towerCollection.entrySet()){
			if(((TowerModel)entry.getValue()).executeStrategy())
				totalHits++;
		}
		return totalHits;
	}

	/**
	 * adds tower to the map
	 * @param location map's coordinates
	 * @param tower the tower object to be placed on map
	 */
	public void addTower(String location,TowerModel tower){
		char[] nameExploded = location.toCharArray();
		int x = Integer.parseInt(String.valueOf(nameExploded[0]));
		int y = Integer.parseInt(String.valueOf(nameExploded[1]));
		if(mapArray[x][y] == 0){
			towerCollection.put(location, tower);
			if(tower.getName().equalsIgnoreCase("Castle Tower"))
			{
				mapArray[x][y] = -7;	
			} else if(tower.getName().equalsIgnoreCase("Imperial Tower"))
			{
				mapArray[x][y] = -8;	
			} else if(tower.getName().equalsIgnoreCase("Industrial Tower"))
			{
				mapArray[x][y] = -9;	
			}
			
			setChanged();
			notifyObservers(this);
		} 

	}

	/**
	 * tells if the map has any item
	 * @param location item's location
	 * @return true if the location doesn't contain any item
	 */
	public Boolean checkMapIsEmpty(String location){
		char[] nameExploded = location.toCharArray();
		int x = Integer.parseInt(String.valueOf(nameExploded[0]));
		int y = Integer.parseInt(String.valueOf(nameExploded[1]));
		if(mapArray[x][y] == 0){
			return true;
		} else
			return false;
	}

	/**
	 * tells if there is any tower on the provided location
	 * @param location map's coordinates
	 * @return true if the location does contain any tower
	 */
	public Boolean checkTowerExists(String location){
		if(towerCollection.containsKey(location))
			return true;
		else{
			char[] nameExploded = location.toCharArray();
			int x = Integer.parseInt(String.valueOf(nameExploded[0]));
			int y = Integer.parseInt(String.valueOf(nameExploded[1]));
			if(mapArray[x][y] == -5)
				return true;
			else
				return false;
		}
	}

	/**
	 * deletes tower form the map provided its location
	 * @param location tower's coordinates
	 * @return true if the tower has been deleted, false otherwise
	 */
	public Boolean deleteTowerFromMap(String location){
		char[] nameExploded = location.toCharArray();
		int x = Integer.parseInt(String.valueOf(nameExploded[0]));
		int y = Integer.parseInt(String.valueOf(nameExploded[1]));
		if(towerCollection.containsKey(location)){
			towerCollection.remove(location);
			mapArray[x][y] = 0;
			return true;
		}else{
			return false;
		}

	}

	/**
	 * returns all the tower's placed on map
	 * @return a hashMap collection containing all the towers
	 */
	public HashMap<String,TowerModel> getTowerCollection(){
		return towerCollection;
	}

	/**
	 * checks and returns boolean if the critter exists on a crtain location
	 * @param location critter's coordinates
	 * @return true if the critter is found on the provided location, false otherwise 
	 */
	public Boolean checkCritterExists(String location){
		Boolean answer=false;
		for (Map.Entry<String, Critter> entry : critterCollection.entrySet()) {
			if(((Critter)entry.getValue()).getMyLocationOnMap().equalsIgnoreCase(location)){	
				answer =  true; break; 
			}
			else 
				answer =  false;			
		}
		return answer;
		
	}


	/**
	 * returns critter provided its location
	 * @param location row/col of the required critter
	 * @return the critter object
	 */
	public Critter getCritter(String location){
		Critter answer=null;
		for (Map.Entry<String, Critter> entry : critterCollection.entrySet()) {
			if(((Critter)entry.getValue()).getMyLocationOnMap().equalsIgnoreCase(location)){	
				answer =  entry.getValue();
				break;
			}
			else 
				answer =  null;			
		}
		return answer;
	}

	/**
	 * returns a critter by key provided
	 * @param key key of the required critter
	 * @return null if there is no critter available of provided key
	 */
	public Critter getCritterFromCollection(String key){
		if(critterCollection.containsKey(key)){
			return critterCollection.get(key);
		}else{
			return null;
		}
	}

	/**
	 * adds  critter to the critter's hashmap collection
	 * @param location the location at which the critter is required
	 * @param critter the critter object itself
	 */
	public void addCritter(String location,Critter critter){
		critterCollection.put(location, critter);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * deletes the critter from a location provided
	 * @param location location of the critter to be deleted
	 */
	public void removeCritter(String location){
		if(critterCollection.containsKey(location)){
			critterCollection.remove(location);
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Checks if the critter's hashmap is empty
	 * @return true/false
	 */
	public boolean isCritterCollectionEmpty(){
		if(critterCollection.size()<1)
			return true;
		else 
			return false;
	}


	/**
	 * Returns all the critters generated in a hash map
	 * @return a ConcurrentHashMap containing all the critte'r information
	 */
	public ConcurrentHashMap<String,Critter> getCritterCollection(){
		return critterCollection;
	}

	/**
	 * Save all the critters generated in a hash map
	 * @param critter a ConcurrentHashMap containing all the critte'r information
	 */
	public void setCritterCollection(ConcurrentHashMap<String,Critter> critter){
		this.critterCollection = critter;
	}

	/**
	 * returns the location if an item on the grid
	 * @param value item type
	 * @return row + column of the required item
	 */
	public String findLocationInMap(int value){
		for(int k=0;k<mapArray.length;k++){
			for(int i=0;i<mapArray[k].length;i++){
				if(mapArray[k][i] == value)
					return String.valueOf(k)+String.valueOf(i);
			}
		}
		return null;
	}

	/**
	 * adds an item to map
	 * @param type item type
	 * - Entry
	 * - Exit
	 * - Path
	 * 
	 * @param row row number of grid
	 * @param col column number of grid
	 * @return the boolean value whether or the the item was added
	 */
	public Boolean addToMap(String type, int row, int col){
		if(row > mapArray.length || col > mapArray[0].length){
			return false;
		}else{
			switch (type){
			case "1":{
				mapArray[row][col] = 1;
				setChanged();
				notifyObservers(this);
				return true;
			}case "9999":{
				mapArray[row][col] = 9999;
				setChanged();
				notifyObservers(this);
				return true;
			}default :{
				try {
					mapArray[row][col] = Integer.parseInt(type);
					setChanged();
					notifyObservers(this);
					return true;
				} catch (Exception e){
					return false;
				}
			}
			}
		}
	}

	/**
	 * deletes an item form map
	 * @param row row number of grid
	 * @param col column number of grid
	 * @return the boolean value whether or the the item was deleted
	 */
	public Boolean deleteFromMap(int row, int col){
		if(row > mapArray.length || col > mapArray[0].length){
			return false;
		}else{
			mapArray[row][col] = 0;
			setChanged();
			notifyObservers(this);
			return true;
		}
	}
}
