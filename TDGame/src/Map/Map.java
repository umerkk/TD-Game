package Map;

import java.util.HashMap;
import code.game.towers.TowerModel;;

class Map {

	private String mapName;
	private int[][] mapArray;
	private HashMap<String,TowerModel> towerCollection = new HashMap<String,TowerModel>();
	private int ArrayRow;
	private int ArrayCol;


	//Constructor
	public Map(String _mapName, int[][] _mapArray)
	{
		this.mapName = _mapName;
		this.mapArray = _mapArray;
	}

	public Map(String _mapName, int Row, int Col)
	{
		this.mapName = _mapName;
		this.mapArray = new int[Row][Col];
	}

	public String GetName()
	{
		return this.mapName;
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
		towerCollection.put(location, tower);
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
				return true;
			}
			case "9999":
			{
				mapArray[Row][Col] = 9999;
				return true;
			}
			default :
			{
				try {
					mapArray[Row][Col] = Integer.parseInt(type);
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
			return true;
		}
		
	}



}
