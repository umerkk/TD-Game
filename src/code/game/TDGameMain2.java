package code.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import code.game.controllers.SingleGameController;
import code.game.models.GameData;
import code.game.models.GameMap;
import code.game.models.MapModel;
import code.game.utils.Util;

import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;


/**
 * This is the view class for Tower Defence Game play, containing all the UI components
 * implementing observer
 * 
 * @author Armaghan
 * @author Lokesh
 * @since Build_1
 */
public class TDGameMain2 implements Observer {

	private JFrame frame;
	JTextArea txtTwrDesc = new JTextArea();
	JLabel lblAccBal = new JLabel("0  ");
	JLabel powerlbl = new JLabel("0  ");
	JLabel wavelbl = new JLabel("0  ");
	JButton btnNewButton = new JButton("Pause");
	JButton btnStrtGame = new JButton("Start Game");
	JButton SaveGameBtn = new JButton("Save Game");
	SingleGameController myController = SingleGameController.getGameControllerInstance();
	public Panel panel = new Panel();
	ScrollPane scPanel = new ScrollPane();
	private int globalWaveCounter = 0;
	private final String DEFAULT_FILE_PATH = System.getProperty("user.dir") + "/SavedGames";




	/**
	 * Overriding abstract method of observer class
	 * @param arg0 observable class status changed
	 * @param arg1 object which sends the event
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		lblAccBal.setText(String.valueOf(((GameData)arg0).getAccountBalance()));
		powerlbl.setText(String.valueOf(((GameData)arg0).getPlayerPower()));
		wavelbl.setText(String.valueOf(((GameData)arg0).getWave()));
		if(String.valueOf(((GameData)arg0).getSelectedTowerDescription()).equalsIgnoreCase("null"))
		{
			txtTwrDesc.setText("Nothing to display at the moment.");
		}else {
			txtTwrDesc.setText(String.valueOf(((GameData)arg0).getSelectedTowerDescription()));
		}
		globalWaveCounter = ((GameData)arg0).getWave();
		if(globalWaveCounter>1){
			btnStrtGame.setText("Start Next Wave");
		} else {
			btnStrtGame.setText("Start Game");
		}
	}

	// Update the map log when global wave counter increasing
	private void updateMapLog(int globalWaveCounter){
		System.out.println("updating map......");
		if(globalWaveCounter>1){
			ArrayList<String> playHistory = myController.getMapModel().getPlayHistory();

			if(playHistory==null || playHistory.size()<1){
				playHistory = new ArrayList<String>();
			}
			playHistory.add(Util.addDate("Played with score : 10"));

			myController.getMapModel().setPlayHistory(playHistory);
			Util.updateMapFile(myController.getMapModel());
		}
	}

	/**
	 * Method to get the current account balance displayed to player
	 * @return current account balance
	 */
	public String getAccBal() { return lblAccBal.getText(); }

	/**
	 * Launch the application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameData mGameData = new GameData();
					GameMap mGameMap = new GameMap();
					TDGameMain2 window = new TDGameMain2(mGameData,mGameMap);

					mGameData.addObserver(window);
					//gamecntrlrobj.addObserver(window);
					window.custmInitializeFrm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor of TDGameMain, initializes the game methods to be drawn
	 * @param mGameData game data model object to be used by view
	 * @param mGameMap game map model to be used by the view
	 */
	public TDGameMain2(GameData mGameData,GameMap mGameMap) {
		myController.setGameDataModel(mGameData);
		myController.setMap(mGameMap);

		initialize();
		custmInitializeFrm();
		
		
	}

	/**
	 * Initialize the frame, is responsible for setting the layout, and placing the grid on the screen
	 */
	public void custmInitializeFrm() {

		panel.setLayout(new MigLayout());
	}
	/**
	 * Method to handle sell button event
	 */
	private void selBtnHandlr(){
		myController.RemoveTower();
	}

	/**
	 * calls a method to upgrade selected tower
	 */
	private void upgradeBtnHandlr(){
		myController.upgradeSelectedTower();
	}

	/**
	 * Initialize the frame contents 
	 * including all the UI components
	 * - Buy/sell Buttons
	 * - Tower inspection panel
	 * - map layout
	 * - Texts
	 * - Towers to select
	 * 
	 * Button listeners.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);		
		frame.setBounds(100, 100, 1081, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel jPnlHdr = new JPanel();
		frame.getContentPane().add(jPnlHdr, BorderLayout.NORTH);
		jPnlHdr.setLayout(new BorderLayout(0, 0));

		JLabel lblGameHdr = new JLabel("Tower Defense Game");
		lblGameHdr.setFont(new Font("Arial Black", Font.BOLD, 34));
		lblGameHdr.setForeground(Color.RED);
		jPnlHdr.add(lblGameHdr, BorderLayout.WEST);

		JPanel pnlHdrSub = new JPanel();
		jPnlHdr.add(pnlHdrSub, BorderLayout.CENTER);

		JLabel lblAccLbl = new JLabel("\r\nAccount Balance : $");
		lblAccLbl.setFont(new Font("Arial", Font.BOLD, 14));
		pnlHdrSub.add(lblAccLbl);
		lblAccLbl.setHorizontalAlignment(SwingConstants.CENTER);

		lblAccBal.setFont(new Font("Arial", Font.PLAIN, 14));
		pnlHdrSub.add(lblAccBal);

		JLabel lblPower = new JLabel("Power");
		lblPower.setHorizontalAlignment(SwingConstants.CENTER);
		lblPower.setFont(new Font("Arial", Font.BOLD, 14));
		pnlHdrSub.add(lblPower);


		powerlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		pnlHdrSub.add(powerlbl);

		JLabel lblWave = new JLabel("Wave");
		lblWave.setHorizontalAlignment(SwingConstants.CENTER);
		lblWave.setFont(new Font("Arial", Font.BOLD, 14));
		pnlHdrSub.add(lblWave);


		wavelbl.setFont(new Font("Arial", Font.PLAIN, 14));
		pnlHdrSub.add(wavelbl);

		JButton btnWaveLog = new JButton("Wave Log");
		btnWaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.showLogWave();
				Util.logGlobal("Wave log was viewed" , true);
			}
		});
		pnlHdrSub.add(btnWaveLog);

		JButton btnMapLog = new JButton("Map Stats log");
		btnMapLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.showMapLog(myController.getMapModel());
				Util.logGlobal("Map log was viewed" , true);
			}
		});
		pnlHdrSub.add(btnMapLog);


		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel jPnlFtr = new JPanel();
		frame.getContentPane().add(jPnlFtr, BorderLayout.SOUTH);
		jPnlFtr.setLayout(new BorderLayout(0, 0));

		JLabel lblArangFtr = new JLabel(" ");
		lblArangFtr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblArangFtr.setIcon(null);
		jPnlFtr.add(lblArangFtr, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		jPnlFtr.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));


		panel_1.add(btnStrtGame, BorderLayout.NORTH);
		SaveGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myController.setSaveGameFlag(true);
		
			}
		});
		
		
		
		panel_1.add(SaveGameBtn, BorderLayout.WEST);
		
		
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//myController.incrementWave(panel);
		
						if(btnNewButton.getText().equalsIgnoreCase("Pause"))
						{
							myController.PauseGame(false);
							btnNewButton.setText("Resume");
						} else {
							myController.PauseGame(true);
							btnNewButton.setText("Pause");
						}
					}
				});
				
						btnNewButton.setEnabled(false);
						
						
						
						
								panel_1.add(btnNewButton, BorderLayout.SOUTH);
		btnStrtGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.startWave(panel);
				btnNewButton.setEnabled(true);

			}
		});

		JPanel jPnlDesc = new JPanel();
		frame.getContentPane().add(jPnlDesc, BorderLayout.EAST);
		jPnlDesc.setLayout(new BorderLayout(0, 0));

		JPanel pnlTwrTopLvl = new JPanel();
		pnlTwrTopLvl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Towers", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		jPnlDesc.add(pnlTwrTopLvl, BorderLayout.NORTH);

		final JLabel lblTwr1 = new JLabel("");
		lblTwr1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				myController.setSelectedTower("lblTwr1");
				txtTwrDesc.setText(myController.showSelectedTowerDesc());
			}
		});

		lblTwr1.setIcon(new ImageIcon("res/tower_1.png"));

		final JLabel lblTwr2 = new JLabel("");
		lblTwr2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//m_ctrlrObj.setTowerDesc("lblTwr2");
				myController.setSelectedTower("lblTwr2");
				txtTwrDesc.setText(myController.showSelectedTowerDesc());
			}
		});

		lblTwr2.setIcon(new ImageIcon("res/tower_2.png"));
		pnlTwrTopLvl.setLayout(new BorderLayout(0, 0));
		pnlTwrTopLvl.add(lblTwr1, BorderLayout.WEST);
		pnlTwrTopLvl.add(lblTwr2, BorderLayout.LINE_END);

		JPanel pnlTwrSndLvl = new JPanel();
		pnlTwrTopLvl.add(pnlTwrSndLvl, BorderLayout.SOUTH);
		pnlTwrSndLvl.setLayout(new BorderLayout(0, 0));

		final JLabel lblTwr3 = new JLabel("");
		lblTwr3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//m_ctrlrObj.setTowerDesc("lblTwr3");
				myController.setSelectedTower("lblTwr3");
				txtTwrDesc.setText(myController.showSelectedTowerDesc());
			}
		});

		lblTwr3.setHorizontalAlignment(SwingConstants.LEFT);
		pnlTwrSndLvl.add(lblTwr3, BorderLayout.WEST);
		lblTwr3.setIcon(new ImageIcon("res/tower_3.png"));

		JPanel pnlTwrDesc = new JPanel();
		pnlTwrDesc.setBorder(new TitledBorder(null, "Console", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		jPnlDesc.add(pnlTwrDesc, BorderLayout.CENTER);
		pnlTwrDesc.setLayout(new BorderLayout(0, 0));
		txtTwrDesc.setEditable(false);
		txtTwrDesc.setFont(new Font("Monospaced", Font.PLAIN, 18));

		pnlTwrDesc.add(txtTwrDesc);

		JButton btnCollectiveTowerLog = new JButton("Collective tower log");
		pnlTwrDesc.add(btnCollectiveTowerLog, BorderLayout.SOUTH);
		btnCollectiveTowerLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.showLogTowerCollective();
				Util.logGlobal("Collective tower log was viewed" , true);
			}
		});

		JPanel pnlBtnColl = new JPanel();
		jPnlDesc.add(pnlBtnColl, BorderLayout.SOUTH);

		JButton btnSellTwr = new JButton("($) Sell");
		btnSellTwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selBtnHandlr();
			}
		});
		pnlBtnColl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlBtnColl.add(btnSellTwr);

		JButton btnUpgrdTwr = new JButton("(\u25B2) Upgrade");
		btnUpgrdTwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upgradeBtnHandlr();
			}
		});
		pnlBtnColl.add(btnUpgrdTwr);

		JButton btnChangeStrategy = new JButton("(\u058E)Change Strategy");
		btnChangeStrategy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.changeStrategyOfTower();
			}
		});
		pnlBtnColl.add(btnChangeStrategy);

		JButton btnTowerLog = new JButton("Tower Log");
		btnTowerLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int towerID = -1;
				try {
					towerID = myController.getSelectedTower().getTowerID();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(towerID==-1){
					Util.showDialog("Please select a tower first.");
				}else{
					Util.showLogTower(myController.getSelectedTower().getName());
					Util.logGlobal(myController.getSelectedTower().getName() + "'s log was viewed", true);
				}

			}
		});
		pnlBtnColl.add(btnTowerLog);

		JLabel lblBtnCollSpcAdj = new JLabel(" ");
		lblBtnCollSpcAdj.setFont(new Font("Tahoma", Font.PLAIN, 35));
		pnlBtnColl.add(lblBtnCollSpcAdj);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenuItem mItemOpenMap = new JMenuItem("Open Map");
		mItemOpenMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// read a file from disk
				try {
					myController.openMap();
					Util.showDialog("Top 5 Scores", Util.getTopFiveScores(myController.getMapModel().getPlayHistory()));
					myController.drawMap(true, panel);

					panel.revalidate();
					panel.repaint();			    		

					Util.logGlobal("Loaded map", true);
				}catch(Exception ioe){
					ioe.printStackTrace();
					return;
				}
			}
		});

		mnGame.add(mItemOpenMap);
		
		JSeparator separator = new JSeparator();
		mnGame.add(separator);
		
		JMenuItem mntmLoadASaved = new JMenuItem("Load a Saved Game");
		mntmLoadASaved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(DEFAULT_FILE_PATH));
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try {
						String k = file.getName();
						String[] sp = k.split("\\.");
						File viewFile = new File(file.getParent()+"\\"+sp[0]+".gameView");
						
						FileInputStream fis = new FileInputStream(file);
						ObjectInputStream ois = new ObjectInputStream(fis);
						
						FileInputStream fisV = new FileInputStream(viewFile);
						ObjectInputStream oisV = new ObjectInputStream(fisV);


						SingleGameController cont = (SingleGameController) ois.readObject();
						Panel serializedPanel = (Panel) oisV.readObject();
						ois.close();
						fis.close();
						oisV.close();
						fisV.close();
						
						myController = cont;
						//panel = serializedPanel;
						
						//Serailizing the pnale but not working while deserializing;
						//Attach the Model to this class as it is being attached in StaticMain. 
						//this is causing much problem.
						myController.gameDataModel.addObserver(TDGameMain2.this);
						myController.NotifyViewAboutEverything();
						myController.drawMap(true, panel);
						myController.drawCritter(myController.getMapModl().GetCritterCollection(), panel);
						panel.revalidate();
						panel.repaint();
						Util.logWave("Saved Game is loaded and now playable.");

						
						

					}catch(IOException ioe){
						ioe.printStackTrace();
						return;
					}catch(ClassNotFoundException c){
						System.out.println("Class not found");
						c.printStackTrace();
						return;
					}
				}
				
			}
		});
		mnGame.add(mntmLoadASaved);
		
		JMenuItem mntmSaveGame = new JMenuItem("Save game");
		mntmSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myController.setSaveGameFlag(true);
			}
		});
		mnGame.add(mntmSaveGame);
		
		JMenu mnLogs = new JMenu("Logs");
		menuBar.add(mnLogs);
		
				JMenuItem mItemGlobalMap = new JMenuItem("Global log");
				mnLogs.add(mItemGlobalMap);
				mItemGlobalMap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Util.showLogGlobal();
					}
				});

	}


}
