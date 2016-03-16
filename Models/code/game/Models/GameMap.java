package code.game.Models;
import java.util.HashMap;
import java.util.Observable;;

public class GameMap extends Observable {

	private String mapName;
	private int[][] mapArray;
	private HashMap<String,TowerModel> towerCollection = new HashMap<String,TowerModel>();
	private HashMap<String,Critter> critterCollection = new HashMap<String,Critter>();
	private int ArrayRow;
	private int ArrayCol;


	//Constructor
	public void Initialize(String _mapName, int[][] _mapArray)
	{
		this.mapName = _mapName;
		this.mapArray = _mapArray;
		this.ArrayRow = this.mapArray.length;
		this.ArrayCol = this.mapArray[0].length;
	}

	public void Initialize(String _mapName, int Row, int Col)
	{
		this.mapName = _mapName;
		this.mapArray = new int[Row][Col];
		this.ArrayRow = this.mapArray.length;
		this.ArrayCol = this.mapArray[0].length;
	}

	public String GetName()
	{
		return this.mapName;
	}

	public int GetArrayRow()
	{
		return this.ArrayRow;
	}

	public int GetArrayCol()
	{
		return this.ArrayCol;
	}

	public int[][] GetMapArray()
	{
		return this.mapArray;
	}

	public TowerModel GetTower(String location)
	{
		return towerCollection.get(location);
	}

	public void AddTower(String location,TowerModel tower)
	{
		char[] name_exploded = location.toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		if(mapArray[x][y] == 0)
		{
			towerCollection.put(location, tower);
			mapArray[x][y] = -5;
			notifyObservers(this);
		} 
		
	}
	
	public Boolean CheckMapIsEmpty(String location)
	{
		char[] name_exploded = location.toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		if(mapArray[x][y] == 0)
		{
			return true;
		} else
			return false;
	}

	public Boolean CheckTowerExists(String location)
	{
		if(towerCollection.containsKey(location))
			return true;
		else
		{
			char[] name_exploded = location.toCharArray();
			int x = Integer.parseInt(String.valueOf(name_exploded[0]));
			int y = Integer.parseInt(String.valueOf(name_exploded[1]));
			if(mapArray[x][y] == -5)
				return true;
			else
				return false;
		}
	}
	
	public Boolean DeleteTowerFromMap(String location)
	{
		char[] name_exploded = location.toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		if(towerCollection.containsKey(location))
		{
			towerCollection.remove(location);
			mapArray[x][y] = 0;
			return true;
		}
		else
		{
			return false;
		}
			
	}
	

	public Boolean CheckCritterExists(String location)
	{
		if(critterCollection.containsKey(location))
			return true;
		else
		{
			char[] name_exploded = location.toCharArray();
			int x = Integer.parseInt(String.valueOf(name_exploded[0]));
			int y = Integer.parseInt(String.valueOf(name_exploded[1]));
			if(mapArray[x][y] == -6)
				return true;
			else
				return false;
		}
	}
	

	public Boolean AddToMap(String type, int Row, int Col)
	{
		if(Row > mapArray.length || Col > mapArray[0].length)
		{
			return false;
		}
		else
		{
			switch (type)
			{
			case "1":
			{
				mapArray[Row][Col] = 1;
				notifyObservers(this);
				return true;
			}
			case "9999":
			{
				mapArray[Row][Col] = 9999;
				notifyObservers(this);
				return true;
			}
			default :
			{
				try {
					mapArray[Row][Col] = Integer.parseInt(type);
					notifyObservers(this);
					return true;
				} catch (Exception e)
				{
					return false;
				}
			}
			}

		}

	}

	public Boolean DeleteFromMap(int Row, int Col)
	{
		if(Row > mapArray.length || Col > mapArray[0].length)
		{
			return false;
		}
		else
		{
			mapArray[Row][Col] = 0;
			notifyObservers(this);
			return true;
		}

	}



}
