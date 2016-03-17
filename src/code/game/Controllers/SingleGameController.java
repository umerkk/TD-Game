package code.game.Controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.game.Models.*;
import code.game.Strategies.NearestStrategy;
import code.game.Strategies.StrongestStrategy;
import code.game.Strategies.WeakestStrategy;
/**
 * @author Umer-PC
 *
 */
public class SingleGameController {

	private GameMap map=null;
	private TowerModel selectedTower=null;
	private Boolean newTowerSelected=false;
	private JPanel selectedCell;
	public GameData gameDataModel;
	private int waveNum=1;
	int critterCreationInterval=2;
	private int critterMovementTime=800;
	private boolean isGameStarted = false;
	private Timer gameTimer=null;
	private final int critterKillPoints=20;
	private final int critterRunAwayPoints=20; 



	//======================================================================================

	public JPanel GetSelectedCell()
	{
		return selectedCell;
	}

	public void SetGameDataModel(GameData gf)
	{
		this.gameDataModel=gf;
	}

	public String ShowSelectedTowerDesc()
	{
		if(selectedTower !=null)
		{
			return selectedTower.getTowerDetails().toString();
		} else
			return "";
	}

	public void SetMap(GameMap _map)
	{
		this.map=_map;
	}

	public void SetSelectedCell(JPanel cell)
	{
		this.selectedCell = cell;
	}

	public void OpenMap()
	{
		JFileChooser filebrwsr = new JFileChooser();

		int result = filebrwsr.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) 
		{
			try
			{
				File selectedFile = filebrwsr.getSelectedFile();
				FileInputStream fis = new FileInputStream(selectedFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				int[][] mapArray = (int[][]) ois.readObject();
				map.Initialize("myMap", mapArray);

				ois.close();
				fis.close();
			}catch(Exception ex){}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please select a map file.", "Warning: File not selected.", JOptionPane.WARNING_MESSAGE);	
		}
	}

	private void DrawMapItem(int type, JPanel cell)
	{

		JLabel t = new JLabel();
		t.setForeground(Color.WHITE);
		t.setFont(new Font("Arial",0,20));

		if(type==1)
		{
			try {
				BufferedImage myPicture = ImageIO.read(new File("res/start.png"));
				t = new JLabel(new ImageIcon(myPicture));
			} catch (IOException e) {
				e.printStackTrace();
			}
			t.setBounds(0, 0, 80, 80);



		} else  if(type==9999)

		{
			try {
				BufferedImage myPicture = ImageIO.read(new File("res/end.png"));
				t = new JLabel(new ImageIcon(myPicture));
			} catch (IOException e) {
				e.printStackTrace();
			}
			t.setBounds(0, 0, 80, 80);



		} else  if(!(type==0)){
			//t.setText("P");
			cell.setBackground(Color.green);
			t.setBounds(0, 0, 80, 80);
		}

		cell.add(t);
	}

	public void DrawMap(boolean isExisting, Panel parentPanel)
	{
		if(isExisting)
		{
			for(int k=0;k<map.GetArrayRow();k++)
			{
				for(int i=0;i<map.GetArrayCol();i++)
				{
					String _append = "";
					if(i==map.GetArrayCol()-1)
					{
						_append = ", wrap";
					} else
					{

					}
					final JPanel temp = new JPanel();
					temp.setName(k +""+ i);
					temp.setBorder(BorderFactory.createEtchedBorder(1));
					temp.addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseClicked(MouseEvent e) {
							click(e,temp);
						}
					});
					if(map.GetMapArray()[k][i]==1)
					{
						DrawMapItem(1, temp);
					}  else if(map.GetMapArray()[k][i]==9999)
					{
						DrawMapItem(9999, temp);
					} else if(map.GetMapArray()[k][i]==0)
					{
						DrawMapItem(0, temp);
					} else
					{
						DrawMapItem(2,temp);
					}
					parentPanel.add(temp, "wmax 80, hmax 80, width 80, height 80" + _append);					
				}
			}
		} else {
			for(int k=0;k<map.GetArrayRow();k++)
			{
				for(int i=0;i<map.GetArrayCol();i++)
				{
					String _append = "";
					if(i==map.GetArrayCol()-1)
					{
						_append = ", wrap";
					} else
					{

					}
					final JPanel temp = new JPanel();
					temp.setName(k +""+ i);
					temp.setBorder(BorderFactory.createEtchedBorder(1));
					temp.addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseClicked(MouseEvent e) {
							click(e,temp);
						}
					});
					parentPanel.add(temp, "wmax 80, hmax 80,width 80, height 80" + _append);
				}
			}
		}
	}

	public void RemoveCritters(Panel panel)
	{

		//for(int s=0;s<panel.getComponentCount();s++)
		//{
		for(int k=0;k<panel.getComponentCount();k++)
		{
			try {
				Object sd = panel.getComponent(k);
				Object sd1 = ((JPanel)panel.getComponent(k)).getComponents()[1];
				if(((JLabel)sd1).getName().equalsIgnoreCase("critter"))
				{
					//panel.remove(k);
					((JPanel)panel.getComponent(k)).remove(1);
				}
				//

			} catch (Exception e) {
				continue;
			}
		}

		//}
	}

	public void DrawCritter(HashMap<String,Critter> critterList, Panel panel)
	{
		for (Map.Entry<String, Critter> entry : map.GetCritterCollection().entrySet()) 
		{
			//panel.validate();
		
			//if(((Critter)entry.getValue()).GetMyLocationOnMap()==null)
			//	continue;
			String key = entry.getKey();
			int loc = Integer.parseInt(key);

			if(loc>0)
			{

				try {
					if(((Critter)entry.getValue()).GetHealth()<1)
					{
						map.RemoveCritter(key);
						gameDataModel.AddMoneyToAccount(critterKillPoints);
						continue;
					}

					else {
						String location = map.FindLocationInMap(loc);
						if(location==null)
						{
							location = map.FindLocationInMap(9999);
						}
						String endLoc = map.FindLocationInMap(9999);
						if(location.equalsIgnoreCase(endLoc))
						{
							//Critter reached the end point.
							gameDataModel.DeductMoneyFromAccount(critterRunAwayPoints);
							map.AddCritter(key, null);
							continue;
							//((Critter)entry).SetHealth(0);

						}
						char[] name_exploded = location.toCharArray();

						for(int s=0;s<panel.getComponentCount();s++)
						{
							if(panel.getComponent(s).getName().equalsIgnoreCase(new String(name_exploded)))
							{
								((Critter)entry.getValue()).SetMyLocationOnMap(new String(name_exploded));
								JLabel t=null;
								//panel.getComponent(s).setBackground(Color.red);
								BufferedImage myPicture;
								try {
									myPicture = ImageIO.read(new File("res/critter.png"));
									t = new JLabel(new ImageIcon(myPicture));
									t.setBounds(0, 0, 40, 40);
									t.setName("critter");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								((JPanel)panel.getComponent(s)).add(t);
							}
						}
					}
				} catch (Exception e) {
					continue;	
				}
			}

		}
		//panel.validate();
		//panel.repaint();


	}

	private void DrawTower(JPanel cell, int response)
	{
		String tempName = cell.getName();
		char[] name_exploded = tempName.toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		int[][] mapArray = map.GetMapArray();
		JLabel t=null;

		switch(response){
		case 0:
		{
			selectedTower.SetStrategy(new NearestStrategy(),map);
			break;
		}
		case 1:{
			selectedTower.SetStrategy(new StrongestStrategy(),map);
			break;
		}
		case 2: {
			selectedTower.SetStrategy(new WeakestStrategy(),map);
			break;
		}
		}
		selectedTower.SetMyLocationOnMap(tempName);

		TowerModel m = selectedTower;

		if(m!=null)
		{
			int l = (gameDataModel.GetAccountBalance()- m.getCostOfTower());
			if(l>-1)
			{

				if(mapArray[x][y] == 0) { 


					if(m.getName().equals("Castle Tower"))
					{
						try {
							BufferedImage myPicture = ImageIO.read(new File("res/tower3.png"));

							t = new JLabel(new ImageIcon(myPicture));
						} catch (Exception e){}


					} else if(m.getName().equals("Imperial Tower"))
					{
						try {
							BufferedImage myPicture = ImageIO.read(new File("res/tower.png"));

							t = new JLabel(new ImageIcon(myPicture));
						} catch (Exception e){}
					}else if(m.getName().equals("Industrial Tower"))
					{
						try {
							BufferedImage myPicture = ImageIO.read(new File("res/tower4.png"));

							t = new JLabel(new ImageIcon(myPicture));
						} catch (Exception e){}
					}
					cell.setBackground(Color.blue);
					//gameDataModel.SetAccountBalance(gameDataModel.GetAccountBalance() - m.getCostOfTower());
					SetSelectedCell(null);
					t.setBounds(0, 0, 80, 80);
					cell.add(t);

					map.AddTower(tempName, selectedTower);
					gameDataModel.DeductMoneyFromAccount(m.getCostOfTower());
				}




			} else {
				JOptionPane.showMessageDialog(null, "You do not have enough money to place a new tower.", "Error:", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			selectedCell = cell;
			selectedTower = m;
		}
	}

	public void RemoveTower()
	{
		if(selectedCell!=null)
		{
			if(map.DeleteTowerFromMap(selectedCell.getName()))
			{
				gameDataModel.AddMoneyToAccount(selectedTower.getRefundValue());
				selectedCell.removeAll();
				selectedCell.setBackground(null);
			} else {
				JOptionPane.showMessageDialog(null, "There is no tower at this location.", "Error:", JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	public void UpgradeSelectedTower()
	{
		if(!(selectedTower.getUpgradeCost()>gameDataModel.GetAccountBalance()))
		{
			selectedTower.upgradeCurrentLevel();
			gameDataModel.DeductMoneyFromAccount(selectedTower.getUpgradeCost());
			gameDataModel.SetSelectedTowerDescription(selectedTower.getTowerDetails().toString());
		} else {
			JOptionPane.showMessageDialog(null, "You do not have enough money to upgrade this tower.", "Error:", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void SetSelectedTower(String towerName)
	{
		if(isGameStarted==false)
		{
			// set the selected tower
			switch(towerName)
			{
			case "lblTwr1" : 
				//m_selctdTower = 1;
				selectedTower = new CastleTower();
				newTowerSelected = true;
				break;
			case "lblTwr2" :
				//m_selctdTower = 2;
				selectedTower = new ImperialTower();
				newTowerSelected = true;
				break;
			case "lblTwr3" :
				//m_selctdTower = 3;
				selectedTower = new IndustrialTower();
				newTowerSelected = true;
				break;
			}
		}

	}


	/**
	 * Method to handle click event of map cell
	 * @param e mouse event
	 * @param cell cell to which event block 
	 */

	public void click(MouseEvent e, JPanel cell) 
	{
		//JOptionPane.showMessageDialog(null, "Clicked");
		boolean overideExisting=false;
		String tempName = cell.getName();
		char[] name_exploded = tempName.toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		//1=StartPoint, 9999=End, 2=Path, 3=Delete
		selectedCell = cell;

		if(map.CheckTowerExists(tempName))
		{
			//Assign the variable to selected tower;
			TowerModel tmpmdl = map.GetTower(tempName);
			selectedTower = tmpmdl;
			gameDataModel.SetSelectedTowerDescription(selectedTower.getTowerDetails().toString());
			newTowerSelected=false;

		} else {
			if(newTowerSelected)
			{
				if(map.CheckMapIsEmpty(tempName))
				{
					JList list = new JList(new String[] {"Nearest First", "Strongest First", "Weakest First"});
					JOptionPane.showMessageDialog(
							null, list, "Select the tower strategy", JOptionPane.QUESTION_MESSAGE);
					int response = list.getSelectedIndex();
					DrawTower(cell,response);
				} else {
					JOptionPane.showMessageDialog(null, "The slot is not empty to place a new tower.", "Warning:", JOptionPane.WARNING_MESSAGE);

				}
				selectedTower = null;
				newTowerSelected=false;

			}
		}

	}

	public void StartWave(final Panel panel)
	{
		//for(int k=1,i=0;k<=waveNum*6;k++,i--)
		//{
		//	map.AddCritter(String.valueOf(i), CritterFactory.getCritter(1,map));
		//}
		ActionListener GamePlay = new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{ 
				IncrementWave(panel);
			}};

	
			gameTimer = new Timer(critterMovementTime,GamePlay);
			gameTimer.start();
			
			isGameStarted = true;
			critterCreationInterval = 2;


	}

	public void IncrementWave(Panel panel)
	{
		

		if(critterCreationInterval%2==0)
		{
			map.AddCritter(String.valueOf(0), CritterFactory.getCritter(1,map));
		}
		//critterCreationInterval = 3;
		critterCreationInterval++;
		HashMap<String,Critter> tempList = new HashMap<String, Critter>();

		for (Map.Entry<String, Critter> entry : map.GetCritterCollection().entrySet()) 
		{
			String key = entry.getKey();
			Critter critter = (Critter) entry.getValue();
			int loc = Integer.parseInt(key);
			loc++;
			tempList.put(String.valueOf(loc), critter);
			//tempList.remove(key);
		}
		RemoveCritters(panel);
		//panel.validate();
		//panel.repaint();
		
		map.SetCritterCollection(tempList);
		DrawCritter(map.GetCritterCollection(),panel);
		panel.repaint();
		map.TowerToShoot();

		if(map.IsCritterCollectionEmpty() || gameDataModel.GetAccountBalance()<1)
		{
			isGameStarted=false;
			gameTimer.stop();
		

		}
	}


}
