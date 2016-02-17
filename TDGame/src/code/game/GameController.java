package code.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;

import code.game.towers.*;

/**
 * This is the game controller class for handling all the user interactions with the User interface.
 * 
 * @author lokesh
 * @author i_iftikh
 * @version 1.0.0.0
 * 
 */
public class GameController extends Observable
{
	private List<CastleTower> m_castltwrobjarr;
	private List<ImperialTower> m_imprltwrobjarr;
	private List<IndustrialTower> m_indstrltwrobjarr;
	private TowerModel m_selctdtower;
	private int m_accbalanc;
	
	public StringBuilder getSelectdTwrDtls()
	{ 
		if(m_selctdtower == null)
			return null;
		else
			return m_selctdtower.getTowerDetails(); 
	}
	
	private void setSelectedTower(TowerModel value)
	{
		m_selctdtower = value;
		setChanged();
		notifyObservers(this);
	}
	public int getAccountBalnc(){ return m_accbalanc; }
	private void setAccBalnc(int value)
	{
		m_accbalanc = value;
		setChanged();
		notifyObservers(this);
	}
	
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
	
	public void initializeCntrolr(TDGameMain parntobj)
	{
		m_selctdtower = null;
		setAccBalnc(120);
		
		m_castltwrobjarr = new ArrayList<CastleTower>();
		m_imprltwrobjarr = new ArrayList<ImperialTower>();
		m_indstrltwrobjarr = new ArrayList<IndustrialTower>();
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
		// set the selected tower
		switch(lblName)
		{
			case "lblTwr1" : 
				//m_selctdTower = 1;
				break;
			case "lblTwr2" :
				//m_selctdTower = 2;
				break;
			case "lblTwr3" :
				//m_selctdTower = 3;
				break;
		}
		
		
	}
	
}
