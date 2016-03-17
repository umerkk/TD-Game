package code.game.Models;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;;

public class GameMap extends Observable {

	private String mapName;
	private int[][] mapArray;
	private int[][] critterPath;
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
		this.critterPath = new int[Row][Col];
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
		if(towerCollection.containsKey(location))
		{
			return towerCollection.get(location);
		} else {
			return null;
		}
	}

	public int TowerToShoot()
	{
		int totalHits=0;
		for (Map.Entry<String, TowerModel> entry : towerCollection.entrySet()) 
		{
			if(((TowerModel)entry.getValue()).ExecuteStrategy())
				totalHits++;
		}
		return totalHits;
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
	public HashMap<String,TowerModel> GetTowerCollection()
	{
		return towerCollection;
	}


	public Boolean CheckCritterExists(String location)
	{
		Boolean answer=false;
		for (Map.Entry<String, Critter> entry : critterCollection.entrySet()) 
		{
			if(((Critter)entry.getValue()).GetMyLocationOnMap().equalsIgnoreCase(location))
			{	answer =  true; break; }
			else 
				answer =  false;			
		}
		return answer;

		//		if(critterCollection.containsKey(location))
		//			return true;
		//		else
		//			return false;
		//		else
		//		{
		//			char[] name_exploded = location.toCharArray();
		//			int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		//			int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		//			if(mapArray[x][y] == -6)
		//				return true;
		//			else
		//				return false;
		//		}
	}


	public Critter GetCritter(String location)
	{

		Critter answer=null;
		for (Map.Entry<String, Critter> entry : critterCollection.entrySet()) 
		{
			if(((Critter)entry.getValue()).GetMyLocationOnMap().equalsIgnoreCase(location))
			{	answer =  entry.getValue();
			break;
			}
			else 
				answer =  null;			
		}
		return answer;
	}
	
	public Critter GetCritterFromCollection(String key)
	{
		if(critterCollection.containsKey(key))
		{
			return critterCollection.get(key);
		}
		else
		{
			return null;
		}
	}

	public void AddCritter(String location,Critter critter)
	{

		critterCollection.put(location, critter);

		notifyObservers(this);


	}
	
	public void RemoveCritter(String location)
	{
		if(critterCollection.containsKey(location))
		{
			critterCollection.remove(location);
		} else {
			
		}
		setChanged();
		notifyObservers(this);
	}
	
	public boolean IsCritterCollectionEmpty()
	{
		if(critterCollection.size()<1)
			return true;
		else 
			return false;
	}


	public HashMap<String,Critter> GetCritterCollection()
	{
		return critterCollection;
	}

	public void SetCritterCollection(HashMap<String,Critter> critter)
	{
		this.critterCollection = critter;
	}

	public String FindLocationInMap(int value)
	{
		for(int k=0;k<mapArray.length;k++)
		{
			for(int i=0;i<mapArray[k].length;i++)
			{
				if(mapArray[k][i] == value)
					return String.valueOf(k)+String.valueOf(i);
			}
		}
		return null;
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
