package code.game;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.JOptionPane;

import code.game.towers.CastleTower;
import code.game.towers.ImperialTower;
import code.game.towers.IndustrialTower;
import code.game.towers.TowerModel;

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
	private Map<String, CastleTower> m_castltwrobjarr;
	private Map<String, CastleTower> m_imprltwrobjarr;
	private Map<String, CastleTower> m_indstrltwrobjarr;
	private int m_accbalanc;
	public TowerModel m_selctdtower;
	public TowerModel m2_selctdtower;
	private boolean m_selctednewTower;
	
	
	public TowerModel getSelectdTwr(){ return m_selctdtower; }
	
	/**
	 * set the selected tower and update the state of observable 
	 * 
	 * @param value new selected tower
	 * @param isnewobj if is new tower object
	 */
	private void setSelectedTower(TowerModel value, boolean isnewobj)
	{
		if(isnewobj)
		{
		
			if(value.getCostOfTower() > m_accbalanc)
			{
				JOptionPane.showMessageDialog(null, "Not enough account balance.", "Warning:", JOptionPane.WARNING_MESSAGE);
				m_selctdtower = null;
			} else {
				m_selctdtower = value;
				m_selctednewTower = false;
				setChanged();
				notifyObservers(this);
			}
		}
		
		
		
	}
	private void setTheSelectedTower(TowerModel value, boolean isnewobj)
	{
		
				m_selctdtower = value;
				m_selctednewTower = false;
				setChanged();
				notifyObservers(this);
			
		
		
		
		
	}
	public void ResetTowerData()
	{
		m2_selctdtower = m_selctdtower;
		m_selctdtower = null;
		
	}
	public int getAccountBalnc(){ return m_accbalanc; }
	public void setAccBalnc(int value)
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
	
	public void initializeCntrolr()
	{
		m_selctdtower = null;
		setAccBalnc(120);
		
		m_castltwrobjarr = new HashMap<String, CastleTower>();
		m_imprltwrobjarr = new HashMap<String, CastleTower>();
		m_indstrltwrobjarr = new HashMap<String, CastleTower>();
	}
	
	
	/**
	 * Method to  handle the start game button click event.
	 */
	public void strtGameBtnHandlr()
	{
		
	}
	
	/**
	 * Method to handle the sell tower button click event
	 * @param keyval for remove remove selected tower
	 */
	public void removeSelctdTower(String keyval)
	{
		//m_selctdtower = 
			
		if(m_castltwrobjarr.containsKey(keyval))
		{
			m_selctdtower = m_castltwrobjarr.get(keyval);
			m_castltwrobjarr.remove(keyval);
		}
		else if(m_imprltwrobjarr.containsKey(keyval))
			m_imprltwrobjarr.remove(keyval);
		else if(m_indstrltwrobjarr.containsKey(keyval))
			m_indstrltwrobjarr.remove(keyval);
		
		int newbalnc = m_accbalanc + m_selctdtower.getRefundValue(); 
		m_selctdtower = null;
		setAccBalnc(newbalnc);
		
	}
	
	public void sellTower(TowerModel tower)
	{
		//m_selctdtower = 
			
				
		int newbalnc = m_accbalanc + tower.getRefundValue(); 
		m_selctdtower = null;
		setAccBalnc(newbalnc);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Method to handle the upgrade tower button click event
	 */
	public void upgrdBtnHandlr()
	{
		if(m_selctdtower == null || m_selctednewTower)
		{
			setSelectedTower(null, false);
			return;
		}
		
		if(m_selctdtower.getUpgradeCost() > m_accbalanc)
		{
			JOptionPane.showMessageDialog(null, "Not enough account balance.", "Warning:", JOptionPane.WARNING_MESSAGE);
			return;
		}
		m_selctdtower.upgradeCurrentLevel();
		setAccBalnc(m_accbalanc - m_selctdtower.getUpgradeCost());
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
				setSelectedTower(new CastleTower(), true);
				break;
			case "lblTwr2" :
				//m_selctdTower = 2;
				setSelectedTower(new ImperialTower(), true);
				break;
			case "lblTwr3" :
				//m_selctdTower = 3;
				setSelectedTower(new IndustrialTower(), true);
				break;
		}
		
		
	}
	
}
