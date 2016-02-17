package code.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import code.game.towers.TowerModel;


/**
 * This is the game controller class for handling all the user interactions with the User interface.
 * 
 * @author lokesh
 * @author i_iftikh
 * @version 1.0.0.0
 * 
 */
public class GameController 
{
	private int m_nooftowers;
	private TowerModel[] m_towerobjs;
	
	/**
	 * This file loads all the maps in the directory Map of the working class.
	 *  
	 *  @return A string list of maps. 
	 */
	public List<String> readMapFiles()
	{
		String dirPath = "/Map";
		List<String> mapFiles = new ArrayList<String>();
		
		try
		{
			File folder = new File(dirPath);
			File[] fileList = folder.listFiles();
	
			for (int i = 0; i < fileList.length; i++) 
			{
			  File file = fileList[i];

			  if (file.isFile() && file.getName().endsWith(".txt")) 
				  mapFiles.add(FileUtils.readFileToString(file));
			}
		}
		catch(Exception ex){}
		return mapFiles;
	}
	
	public void initializeTowers()
	{
		
	}
	
	/**
	 * Method to handle the load map button 
	 */
	public void loadMapBtnHandlr()
	{
		
	}
	
	/**
	 * Method to  handle the start game button click event.
	 */
	public void strtGameBtnHandlr()
	{
		
	}
	
	/**
	 * Method to handle the sell tower button click event
	 */
	public void selBtnHandlr()
	{
		
	}
	
	/**
	 * Method to handle the upgrade tower button click event
	 */
	public void upgrdBtnHandlr()
	{
		
	}
	
	/**
	 * Method to handle the tower selection and display details of the tower in the tower description space.
	 * The towers are as follows : lblTwr1, lblTwr2, lblTwr3.
	 * 
	 * @param lblName Name of the label holding the tower.
	 */
	public void setTowerDesc(String lblName)
	{
		// set the txtTwrDesc object here based on label names
	}
	
}
