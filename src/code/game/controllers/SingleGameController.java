package code.game.controllers;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.game.models.Critter;
import code.game.models.CritterFactory;
import code.game.models.GameData;
import code.game.models.GameMap;
import code.game.models.MapModel;
import code.game.models.TowerCastle;
import code.game.models.TowerImperial;
import code.game.models.TowerIndustrial;
import code.game.models.TowerModel;
import code.game.strategies.StrategyNearest;
import code.game.strategies.StrategyNearestToEndPoint;
import code.game.strategies.StrategyStrongest;
import code.game.strategies.StrategyWeakest;
import code.game.utils.Util;
import net.miginfocom.swing.MigLayout;



/**This class is the main controller of the project and act as a singleton controller
 * i.e is being used as a Single Pattern.
 * @author Umer
 * @author Iftikhar
 *
 */
public class SingleGameController implements Serializable {

	private static SingleGameController instance;
	private GameMap gameMap=null;
	private TowerModel selectedTower=null;
	private Critter selectedCritter=null;
	private Boolean newTowerSelected=false;
	private JPanel selectedCell;
	public GameData gameDataModel;
	int critterCreationInterval=2;
	private int critterMovementTime=500;
	public boolean isGameStarted = false;
	private Timer gameTimer=null;
	private final int CRITTER_KILL_POINTS =5;
	private final int CRITTER_RUN_AWAY_POINTS =20; 
	private final int POINT_ENTRY = 1;
	private final int POINT_EXIT = 9999;
	private final int CASTLE_TOWER_ENTRY = -7;
	private final int IMPERIAL_TOWER_ENTRY = -8;
	private final int INDUSTRIAL_TOWER_ENTRY = -9;
	private int numberOfCritters=0;
	private boolean saveGameFlag=false;
	private MapModel mapModel;

	/**
	 * getter for a the MapModeler
	 * @return MapModel
	 */
	public MapModel getMapModel() {
		return mapModel;
	}

	/**
	 * setter for a MapModeler
	 * @param newMapModel to set the map
	 */
	public void setMapModel(MapModel newMapModel) {
		this.mapModel = newMapModel;
	}

	public void setSaveGameFlag(boolean b) {
		this.saveGameFlag =b;
	}

	/**
	 * empty constructor
	 */
	private SingleGameController(){
	}

	/**
	 * Makes sure that the game have one and only one instance of game controller
	 * @return returns the initialized instance
	 */
	public static SingleGameController getGameControllerInstance() {
		if(instance==null) {
			instance = new SingleGameController();
			return instance;
		} else {
			return instance;
		}
	}

	/**
	 * getter for a currently selected cell
	 * @return selected cell
	 */
	public JPanel getSelectedCell() {
		return selectedCell;
	}

	/**
	 * Method to get the currently selected tower
	 * @return returns the currently selected tower
	 */
	public TowerModel getSelectedTower() { return selectedTower; }

	/**
	 * sets the data model
	 * @param mGameData GameData object
	 */
	public void setGameDataModel(GameData mGameData) {
		this.gameDataModel = mGameData;
	}

	/**
	 * shows selected tower's details on tower inspection panel
	 * @return tower details
	 */
	public String showSelectedTowerDesc() {
		if(selectedTower !=null){
			return selectedTower.getTowerDetails().toString();
		} else
			return "";
	}

	/**
	 * sets the map 
	 * @param map gameMap object
	 */
	public void setMap(GameMap map) {
		this.gameMap=map;
	}

	/**
	 * sets the clicked cell as currently selected cell.
	 * @param cell cell to be set
	 */
	public void setSelectedCell(JPanel cell) {
		this.selectedCell = cell;
	}

	/**
	 * Method to get the current map model
	 * @return current map model
	 */
	public GameMap getMapModl() { 
		return gameMap;
	}

	/**
	 * Gets, opens and initializes the map file from the file directory using input stream reader 
	 */
	public void openMap() {
		JFileChooser fileBrwsr = new JFileChooser();

		int result = fileBrwsr.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {

			File selectedFile = fileBrwsr.getSelectedFile();
			readFromFile(selectedFile);

		} else {
			JOptionPane.showMessageDialog(null, "Please select a map file.", "Warning: File not selected.", JOptionPane.WARNING_MESSAGE);	
		}
	}

	/**
	 * Method to read file from the selected file and set the map model object.
	 * 
	 * @param selectedFile map file to be read. 
	 */
	public void readFromFile(File selectedFile) {
		try {
			FileInputStream fis = new FileInputStream(selectedFile);
			ObjectInputStream ois = new ObjectInputStream(fis);

			mapModel = (MapModel) ois.readObject();
			mapModel.setFilePath(selectedFile);
			int[][] mapArray = mapModel.getMapArray();
			gameMap.initialize("myMap", mapArray);


			ois.close();
			fis.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Draws the contents of the map
	 *  - Start point
	 *  - Exit point
	 *  - Path
	 *  
	 * @param type defines if the item to be drawn is path, entry or exit point
	 * @param cell the grid location to be drawn
	 */
	private void drawMapItem(int type, JPanel cell) {

		JLabel t = new JLabel();
		t.setForeground(Color.WHITE);
		t.setFont(new Font("Arial",0,20));

		if(type == POINT_ENTRY) {
			try {
				BufferedImage myPicture = ImageIO.read(new File("res/start.png"));
				t = new JLabel(new ImageIcon(myPicture));
			} catch (IOException e) {
				e.printStackTrace();
			}
			t.setBounds(0, 0, 80, 80);

		} else if(type == POINT_EXIT) {
			try {
				BufferedImage myPicture = ImageIO.read(new File("res/end.png"));
				t = new JLabel(new ImageIcon(myPicture));
			} catch (IOException e) {
				e.printStackTrace();
			}
			t.setBounds(0, 0, 80, 80);

		} else if(!(type==0)){
			cell.setBackground(Color.green);
			t.setBounds(0, 0, 80, 80);
		}

		cell.add(t);
	}

	/**
	 * Draws the whole map on the screen, gets parent panel as a parameter
	 * adds the mouse listeners for event handling
	 * 
	 * @param isExisting if the map is existing
	 * @param parentPanel parent panel on which map is to be drawn
	 */
	public void drawMap(boolean isExisting, Panel parentPanel) {
		if(isExisting) {
			parentPanel.removeAll();
			parentPanel.setLayout(new MigLayout());
			//parentPanel.revalidate();
			parentPanel.repaint();

			for(int k=0;k<gameMap.getArrayRow();k++) {
				for(int i=0;i<gameMap.getArrayCol();i++) {

					String append = "";
					if(i==gameMap.getArrayCol()-1) {
						append = ", wrap";
					} else {

					}

					final JPanel temp = new JPanel();
					temp.setName(k +""+ i);
					temp.setBorder(BorderFactory.createEtchedBorder(1));
					temp.addMouseListener(new MouseListener() {
						/**
						 * Override methods for MouseEvent
						 */
						@Override
						public void mouseReleased(MouseEvent e) {
						}

						@Override
						public void mousePressed(MouseEvent e) {
						}

						@Override
						public void mouseExited(MouseEvent e) {
						}

						@Override
						public void mouseEntered(MouseEvent e) {
						}

						@Override
						public void mouseClicked(MouseEvent e) {
							click(e,temp);
						}
					});

					if(gameMap.getMapArray()[k][i]==1) {
						drawMapItem(1, temp);
					}  else if(gameMap.getMapArray()[k][i] == POINT_EXIT) {
						drawMapItem(POINT_EXIT, temp);
					} else if(gameMap.getMapArray()[k][i]==0) {
						drawMapItem(0, temp);
					} else if(gameMap.getMapArray()[k][i]==CASTLE_TOWER_ENTRY) {
						drawTowerFromLoadGame(CASTLE_TOWER_ENTRY,temp);
					} else if(gameMap.getMapArray()[k][i]==IMPERIAL_TOWER_ENTRY) {
						drawTowerFromLoadGame(IMPERIAL_TOWER_ENTRY,temp);
					} else if(gameMap.getMapArray()[k][i]==INDUSTRIAL_TOWER_ENTRY) {
						drawTowerFromLoadGame(INDUSTRIAL_TOWER_ENTRY,temp);
					} else {
						drawMapItem(2,temp);
					}

					parentPanel.add(temp, "wmax 80, hmax 80, width 80, height 80" + append);					
				}
			}

		} else {
			for(int k=0;k<gameMap.getArrayRow();k++) {
				for(int i=0;i<gameMap.getArrayCol();i++) {
					String appendStr = "";
					if(i==gameMap.getArrayCol()-1) {
						appendStr = ", wrap";
					} else {

					}
					final JPanel temp = new JPanel();
					temp.setName(k +""+ i);
					temp.setBorder(BorderFactory.createEtchedBorder(1));
					temp.addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent e) {
						}

						@Override
						public void mousePressed(MouseEvent e) {
						}

						@Override
						public void mouseExited(MouseEvent e) {
						}

						@Override
						public void mouseEntered(MouseEvent e) {
						}

						@Override
						public void mouseClicked(MouseEvent e) {
							click(e,temp);
						}
					});
					parentPanel.add(temp, "wmax 80, hmax 80,width 80, height 80" + appendStr);
				}
			}
		}
	}
/**
 * Load game and draw the tower
 * @param type type of tower
 * @param cell tower's placement area
 */
	private void drawTowerFromLoadGame(int type, JPanel cell) {

		BufferedImage myPicture=null;
		JLabel t=null;

		switch(type){
		case CASTLE_TOWER_ENTRY: {
			try {
				myPicture = ImageIO.read(new File("res/tower_1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t = new JLabel(new ImageIcon(myPicture));
			break;
		} case IMPERIAL_TOWER_ENTRY: {
			try {
				myPicture = ImageIO.read(new File("res/tower_2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t = new JLabel(new ImageIcon(myPicture));
			break;
		} case INDUSTRIAL_TOWER_ENTRY: {
			try {
				myPicture = ImageIO.read(new File("res/tower_3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t = new JLabel(new ImageIcon(myPicture));
			break;
		}
		}
		cell.setBackground(Color.white);
		setSelectedCell(null);
		t.setBounds(0, 0, 80, 80);
		cell.add(t);

	}

	/**
	 * As soon as the critter is killed this method is called and removes it from the screen.
	 * @param panel panel from which critter is to be removed
	 */
	public void removeCritters(Panel panel) {

		//for(int s=0;s<panel.getComponentCount();s++)
		//{
		for(int k=0;k<panel.getComponentCount();k++) {
			try {
				Object sd1 = ((JPanel)panel.getComponent(k)).getComponents()[1];
				if(((JLabel)sd1).getName().equalsIgnoreCase("critter")) {
					//panel.remove(k);
					((JPanel)panel.getComponent(k)).remove(1);
				}

			} catch (Exception e) {
				continue;
			}
		}

		//}
	}

	/**
	 * When the gameplay starts this method is called repeatedly, 
	 * gets the critter information to be drawn on the screen
	 * @param critterList list of certain number of critter to be drawn
	 * @param panel used in drawing and adding component
	 */
	public void drawCritter(ConcurrentHashMap<String,Critter> critterList, Panel panel) {

		for (Map.Entry<String, Critter> entry : gameMap.getCritterCollection().entrySet()) {
			try {
				String key = entry.getKey();
				int loc = Integer.parseInt(key);

				if(loc>0) {

					if(((Critter)entry.getValue()).getHealth()<1) {
						gameMap.removeCritter(key);
						gameDataModel.addMoneyToAccount(CRITTER_KILL_POINTS);
						continue;
					}

					else {
						String location = gameMap.findLocationInMap(loc);
						if(location==null) {
							location = gameMap.findLocationInMap(POINT_EXIT);
						}
						String endLoc = gameMap.findLocationInMap(POINT_EXIT);
						if(location.equalsIgnoreCase(endLoc)) {
							//if(drawController%2==0){
							gameDataModel.deductPlayerPower(CRITTER_RUN_AWAY_POINTS);
							gameMap.removeCritter(key);
							//}
							continue;

						}
						char[] nameExploded = location.toCharArray();

						for(int s=0;s<panel.getComponentCount();s++) {
							if(panel.getComponent(s).getName().equalsIgnoreCase(new String(nameExploded))) {
								((Critter)entry.getValue()).setMyLocationOnMap(new String(nameExploded));
								JLabel t=null;
								//panel.getComponent(s).setBackground(Color.red);
								BufferedImage myPicture;
								try {
									myPicture = ImageIO.read(new File("res/critter.png"));
									t = new JLabel(new ImageIcon(myPicture));
									switch(((Critter)entry.getValue()).getBackground())
									{
									case "red":{

										myPicture = ImageIO.read(new File("res/critter_burning.png"));
										t = new JLabel(new ImageIcon(myPicture));
										//t.setBackground(Color.red);
										break;
									}
									case "blue":{
										myPicture = ImageIO.read(new File("res/critter_freezing.png"));
										t = new JLabel(new ImageIcon(myPicture));
										((Critter)entry.getValue()).setBackground("none");
										break;
									}
									case "black":{
										t.setBackground(Color.black);
										myPicture = ImageIO.read(new File("res/critter_splash.png"));
										t = new JLabel(new ImageIcon(myPicture));
										((Critter)entry.getValue()).setBackground("none");
									} default: {

									}
									}
									t.setBounds(0, 0, 80, 80);

									t.setName("critter");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								((JPanel)panel.getComponent(s)).add(t);

							}
						}
					}

				} else {
					//map.RemoveCritter(key);
					//continue;
				}
			} catch (Exception e) {
				continue;	
			}
		}
		//panel.validate();
		//panel.repaint();

	}

	/**
	 * The main method to draw the tower on the map. 
	 * Makes sure that the player have enough coins to place that tower on the map.
	 * 
	 * @param cell the grid location derived from user's click event
	 * @param response indicates the type of strategy the user selected while placing the tower
	 */
	private void drawTower(JPanel cell, int response) {
		String tempName = cell.getName();
		char[] nameExploded = tempName.toCharArray();
		int x = Integer.parseInt(String.valueOf(nameExploded[0]));
		int y = Integer.parseInt(String.valueOf(nameExploded[1]));
		int[][] mapArray = gameMap.getMapArray();
		JLabel t=null;

		switch(response){
		case 0: {
			selectedTower.setStrategy(new StrategyNearest(),gameMap);
			break;
		}
		case 1: {
			selectedTower.setStrategy(new StrategyStrongest(),gameMap);
			break;
		}
		case 2: {
			selectedTower.setStrategy(new StrategyWeakest(),gameMap);
			break;
		}
		case 3: {
			selectedTower.setStrategy(new StrategyNearestToEndPoint(),gameMap);
			break;
		}
		}
		selectedTower.setMyLocationOnMap(tempName);

		TowerModel tModel = selectedTower;

		if(tModel!=null) {
			int l = (gameDataModel.getAccountBalance()- tModel.getCostOfTower());
			if(l>-1) {

				if(mapArray[x][y] == 0) { 

					if(tModel.getName().equals("Castle Tower")) {
						try {
							BufferedImage myPicture = ImageIO.read(new File("res/tower_1.png"));

							t = new JLabel(new ImageIcon(myPicture));
						} catch (Exception e){}


					} else if(tModel.getName().equals("Imperial Tower")) {
						try {
							BufferedImage myPicture = ImageIO.read(new File("res/tower_2.png"));
							t = new JLabel(new ImageIcon(myPicture));
						} catch (Exception e){}
					}else if(tModel.getName().equals("Industrial Tower")) {
						try {
							BufferedImage myPicture = ImageIO.read(new File("res/tower_3.png"));
							t = new JLabel(new ImageIcon(myPicture));
						} catch (Exception e){}
					}
					cell.setBackground(Color.white);
					//gameDataModel.SetAccountBalance(gameDataModel.GetAccountBalance() - m.getCostOfTower());
					setSelectedCell(null);
					t.setBounds(0, 0, 80, 80);
					cell.add(t);

					gameMap.addTower(tempName, selectedTower);
					gameDataModel.deductMoneyFromAccount(tModel.getCostOfTower());
				}

				Util.logTower(getCurrentTowerName(), getCurrentTowerName() + " was placed on map with Strategy = "+selectedTower.getStrategy().getStrategyName());
			} else {
				JOptionPane.showMessageDialog(null, "You do not have enough money to place a new tower.", "Error:", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			selectedCell = cell;
			selectedTower = tModel;
		}
	}

	/**
	 * Removes the selected tower from the map and updates the coin balance with refund rate of tower.
	 */
	public void removeTower() {
		if(selectedCell!=null) {
			if(gameMap.deleteTowerFromMap(selectedCell.getName())) {
				gameDataModel.addMoneyToAccount(selectedTower.getRefundValue());
				selectedCell.removeAll();
				selectedCell.setBackground(null);

				Util.logTower(getCurrentTowerName(), getCurrentTowerName() + " was sold for " + selectedTower.getRefundValue());

			} else {
				JOptionPane.showMessageDialog(null, "There is no tower at this location.", "Error:", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Upgrades the selected tower, checks account balance to
	 *  make sure if the player has enough coins.
	 */
	public void upgradeSelectedTower() {
		if(!(selectedTower.getUpgradeCost()>gameDataModel.getAccountBalance())) {
			selectedTower.upgradeCurrentLevel();
			gameDataModel.deductMoneyFromAccount(selectedTower.getUpgradeCost());
			gameDataModel.setSelectedTowerDescription(selectedTower.getTowerDetails().toString());

			Util.logTower(getCurrentTowerName(), getCurrentTowerName() + " was upgraded to level " + selectedTower.getCurrentLevel());

		} else {
			//JOptionPane.showMessageDialog(null, "You do not have enough money to upgrade this tower.", "Error:", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * When the user clicks on a certain tower, it selects that tower as current tower
	 * takes a parameter of tower's name 
	 * @param towerName name of tower
	 */
	public void setSelectedTower(String towerName) {
		if(isGameStarted==false) {
			// set the selected tower
			switch(towerName) {
			case "lblTwr1" : 
				//m_selctdTower = 1;
				selectedTower = new TowerCastle();
				newTowerSelected = true;
				break;
			case "lblTwr2" :
				//m_selctdTower = 2;
				selectedTower = new TowerImperial();
				newTowerSelected = true;
				break;
			case "lblTwr3" :
				//m_selctdTower = 3;
				selectedTower = new TowerIndustrial();
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
	public void click(MouseEvent e, JPanel cell) {
		//JOptionPane.showMessageDialog(null, "Clicked");
		boolean overideExisting=false;
		final String tempName = cell.getName();
		final char[] nameExploded = tempName.toCharArray();
		int x = Integer.parseInt(String.valueOf(nameExploded[0]));
		int y = Integer.parseInt(String.valueOf(nameExploded[1]));
		//1=StartPoint, 9999=End, 2=Path, 3=Delete
		selectedCell = cell;

		if(gameMap.checkTowerExists(tempName)) {

			TowerModel tmpMdl = gameMap.getTower(tempName);
			selectedTower = tmpMdl; //Assign the variable to selected tower;
			gameDataModel.setSelectedTowerDescription(selectedTower.getTowerDetails().toString());
			newTowerSelected=false;


		} else if(gameMap.checkCritterExists(tempName))
		{
			selectedCritter = gameMap.getCritter(tempName);
			gameDataModel.setSelectedTowerDescription(selectedCritter.getCritterDetails().toString());
		}
		else
		{
			if(newTowerSelected) {
				if(gameMap.checkMapIsEmpty(tempName)) {
					JList list = new JList(new String[] {"Nearest First", "Strongest First", "Weakest First", "Nearest to End Point"});
					JOptionPane.showMessageDialog(
							null, list, "Select the tower strategy", JOptionPane.QUESTION_MESSAGE);
					int response = list.getSelectedIndex();
					drawTower(cell,response);
				} else {
					JOptionPane.showMessageDialog(null, "The slot is not empty to place a new tower.", "Warning:", JOptionPane.WARNING_MESSAGE);

				}
				selectedTower = null;
				newTowerSelected=false;
			}
		}

	}

	/**
	 * To set the new strategy
	 * @param response to select new strategy
	 * @return tower log
	 */
	public boolean setNewStrategy(int response)
	{
		switch(response){
		case 0: {
			selectedTower.setStrategy(new StrategyNearest(),gameMap);
			break;
		}
		case 1: {
			selectedTower.setStrategy(new StrategyStrongest(),gameMap);
			break;
		}
		case 2: {
			selectedTower.setStrategy(new StrategyWeakest(),gameMap);
			break;
		}
		}

		return Util.logTower(getCurrentTowerName(), "Strategy " 
				+ selectedTower.getStrategy().getStrategyName() + " was selected for " + getCurrentTowerName());
	}

	/**
	 * Shows a dialog containing the list of all the available strategies for the tower being placed
	 * on the map. 
	 * 1- "Nearest First"
	 * 2- "Strongest First"
	 * 3- "Weakest First"
	 */
	public void changeStrategyOfTower(){
		if(selectedTower!=null){
			JList list = new JList(new String[] {"Nearest First", "Strongest First", "Weakest First","Nearest to End Point"});
			JOptionPane.showMessageDialog(
					null, list, "Select the tower strategy", JOptionPane.QUESTION_MESSAGE);

			setNewStrategy(list.getSelectedIndex());

			gameDataModel.setSelectedTowerDescription(selectedTower.getTowerDetails().toString());
		} 
		else {
			JOptionPane.showMessageDialog(null, "Please select a tower first.", "Error:", JOptionPane.ERROR_MESSAGE);

		}
	}
/**
 * To get tower ID
 * @return tower
 */
	private int getCurrentTowerID(){
		return selectedTower.getTowerID();
	}
/**
 * To get name of current tower
 * @return name
 */
	private String getCurrentTowerName(){
		return selectedTower.getName();
	}
	/**
	 * Starts a new wave once called, it is only called once and then the incrementWave() method takes over.
	 * and keeps increment the waves to next level
	 * @param panel map panel
	 */
	public void startWave(final Panel panel) {
		if(this.numberOfCritters<1)
		{
			if(gameDataModel.getWave() < 5) {
				this.numberOfCritters = gameDataModel.getWave()*5;
			} else if(gameDataModel.getWave() >= 5 && gameDataModel.getWave() < 10) {
				this.numberOfCritters = gameDataModel.getWave()*7;
			} else if(gameDataModel.getWave() >= 10) {
				this.numberOfCritters = gameDataModel.getWave()*10;
			} else {
				this.numberOfCritters = gameDataModel.getWave()*10;
			}
		}
		ActionListener gamePlay = new ActionListener() {
			public void actionPerformed(ActionEvent evt) { 
				incrementWave(panel);
			}};

			gameTimer = new Timer(critterMovementTime,gamePlay);
			gameTimer.start();

			isGameStarted = true;
			critterCreationInterval = 2;

			Util.logWave("Critter wave was started");
	}

/**
 * To pause the game in any stage
 * @param b boolean value
 */
	public void pauseGame(boolean b)
	{
		isGameStarted=b;
		if(b)
			Util.logWave("Game is resumed");
		else
			Util.logWave("Game is paused");
	}

/**
 * Notify observers
 */
	public void notifyViewAboutEverything()
	{
		gameDataModel.notifyObservers();
		gameMap.notifyObservers();

	}
/**
 * To save all the game data
 * @param panel for save game
 * @param file for game log
 * @param fileView for save game log
 */
	public void saveGameData(Panel panel, File file, File fileView)
	{
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (Exception e){

			}
		}

		if(!fileView.exists())
		{
			try {
				fileView.createNewFile();
			} catch (Exception e){

			}
		}
		try {
			saveGameFlag=false;
			FileOutputStream fos= new FileOutputStream(file);
			ObjectOutputStream oos= new ObjectOutputStream(fos); 
			oos.writeObject(this);
			oos.close();
			fos.close();

			FileOutputStream foss= new FileOutputStream(fileView);
			ObjectOutputStream ooss= new ObjectOutputStream(foss); 
			ooss.writeObject(panel);
			ooss.close();
			foss.close();

		} catch (Exception e)
		{
			//JOptionPane.showMessageDialog(null, "The game could not be saved at this time. please try again later.");

		}
	}

	/**
	 * Responsible for incrementing wave level, takes a parameter
	 * @param panel used to repaint/invalidate used panel
	 */
	public void incrementWave(Panel panel) {

		if(saveGameFlag)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			java.util.Date date = new java.util.Date();
			File file = new File("SavedGames//"+dateFormat.format(date)+".gameData");
			File fileView = new File("SavedGames//"+dateFormat.format(date)+".gameView");

			saveGameData(panel, file, fileView);
			JOptionPane.showMessageDialog(null, "Game Saved !");
		}
		if(isGameStarted)
		{
			if(!(numberOfCritters<1)){	
				if(critterCreationInterval%2==0) {
					if(gameDataModel.getWave() < 5) {
						gameMap.addCritter(String.valueOf(1), CritterFactory.getCritter(1,gameMap));
					} else if(gameDataModel.getWave() >= 5 && gameDataModel.getWave() < 10) {
						gameMap.addCritter(String.valueOf(1), CritterFactory.getCritter(2,gameMap));
					} else if(gameDataModel.getWave() >= 10) {
						gameMap.addCritter(String.valueOf(1), CritterFactory.getCritter(3,gameMap));
					} else {
						gameMap.addCritter(String.valueOf(1), CritterFactory.getCritter(3,gameMap));
					}
					numberOfCritters--;
				}
				critterCreationInterval++;
			}

			ConcurrentHashMap<String,Critter> tempList = new ConcurrentHashMap<String, Critter>();

			for (Map.Entry<String, Critter> entry : gameMap.getCritterCollection().entrySet()) {
				String key = entry.getKey();
				Critter critter = (Critter) entry.getValue();
				int loc = Integer.parseInt(key);
				if(critter.getDamageTime()==1)
				{
					if(critter.getLastHitBy().equalsIgnoreCase("Castle Tower"))
					{
						critter.setBackground("red");
						critter.reduceHealth(10);
					} else if(critter.getLastHitBy().equalsIgnoreCase("Imperial Tower"))
					{
						critter.setBackground("blue");
						critter.reduceSpeed(1);
					}


				} else if(critter.getDamageTime()==0){
					critter.resetSpeed();
					critter.setBackground("none");

				}
				if(critter.getDamageTime()>0)
				{
					critter.setDamageTime(critter.getDamageTime()-1);
				}
				loc+=critter.getSpeed();
				tempList.put(String.valueOf(loc), critter);
				//tempList.remove(key);
			}

			removeCritters(panel);
			//panel.validate();
			panel.repaint();

			gameMap.setCritterCollection(tempList);

			panel.repaint();

			drawCritter(gameMap.getCritterCollection(),panel);
			//drawController++;
			removeCritters(panel);
			gameDataModel.addMoneyToAccount(gameMap.towerToShoot()*3);
			drawCritter(gameMap.getCritterCollection(),panel);
			Util.logWave("Game timer was incremented - Critters were advanced and Towers shot within their range.");

			if(gameMap.isCritterCollectionEmpty()) {
				isGameStarted=false;
				numberOfCritters=0;
				gameTimer.stop();
				removeCritters(panel);
				gameDataModel.resetPlayerPower();
				gameMap.getCritterCollection().clear();
				JOptionPane.showMessageDialog(null, "You Won!. All the critters are history and you still have some power.", "YAY:", JOptionPane.INFORMATION_MESSAGE);
				gameDataModel.setWaveIncrement();
				gameDataModel.addMoneyToAccount(20);
				Util.logWave("Player won the wave");
				calculateAndUpdateScores();
				Util.showDialog("Top 5 Scores", Util.getTopFiveScores(mapModel.getPlayHistory()));
			} else if(gameDataModel.getPlayerPower()<1) {
				isGameStarted=false;
				numberOfCritters=0;
				gameTimer.stop();
				removeCritters(panel);
				gameMap.getCritterCollection().clear();
				JOptionPane.showMessageDialog(null, "You Lose!.\r\nCritters has taken all of your power.", "Aww:", JOptionPane.ERROR_MESSAGE);
				gameDataModel.resetPlayerPower();
				gameDataModel.resetWave();
				Util.logWave("Player lost the wave");
				calculateAndUpdateScores();
				Util.showDialog("Top 5 Scores", Util.getTopFiveScores(mapModel.getPlayHistory()));
			}
		}
	}
/**
 * To calculate and update the score
 */
	public void calculateAndUpdateScores() {
		int money = gameDataModel.getAccountBalance();
		int power = gameDataModel.getPlayerPower();
		int score = money*power;
		mapModel.getPlayHistory().add(Util.addDate("Gameplay score:: " + score));
		Util.updateMapFile(mapModel);

	}


}
