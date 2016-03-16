package code.game;

import java.awt.EventQueue;
import code.game.Controllers.*;
import code.game.Controllers.SingleGameController;
import code.game.Models.GameData;
import code.game.Models.GameMap;
import code.game.Models.TowerModel;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.ScrollPane;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


/**
 * This is the view class for Tower Defance Game play.
 * 
 * @author Alaa
 * @author lokesh
 * @since Build_1
 */

public class TDGameMain2 implements Observer {

	private JFrame m_frame;
	JTextArea m_txtTwrDesc = new JTextArea();
	JLabel m_lblAccBal = new JLabel("0  ");
	SingleGameController myController = Controller.getGameControllerInstance();
	
	
	int[][] mapArray;
	ScrollPane sc_panel = new ScrollPane();
	//Panel panel_1 = new Panel();
	Panel panel = new Panel();
	int ArrayRow;
	int ArrayCol;
	int selectedTool = 0;
	
	JPanel selectedCell;
	TowerModel selectedTower;
	/**
	 * Overriding abstract method of observer class
	 * @param arg0 observable class status changed
	 * @param arg1 object which sends the event
	 */
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		m_lblAccBal.setText(String.valueOf(((GameData)arg0).GetAccountBalance()));
		m_txtTwrDesc.setText(String.valueOf(((GameData)arg0).GetSelectedTowerDescription()));
		//m_txtTwrDesc.setText(null);
		//TowerModel towrdesc = ((SingleGameController)arg0).getSelectdTwr();
		//if(towrdesc == null)
	//		return;
	//	m_txtTwrDesc.append(towrdesc.getTowerDetails().toString());
	}
	
	
	/**
	 * Launch the application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameData gf = new GameData(500);
					GameMap map = new GameMap();
					TDGameMain2 window = new TDGameMain2(gf,map);
					
					gf.addObserver(window);
					//gamecntrlrobj.addObserver(window);
					window.custmInitializeFrm();
					window.m_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Constructor of TDGameMain.
	 * @param gamcntrlrobj gameController object
	 */
	public TDGameMain2(GameData gf,GameMap map) {
	
		myController.SetGameDataModel(gf);
		myController.SetMap(map);
		
		
		initialize();
		
		custmInitializeFrm();
	}
	
	/**
	 * Initialize the frame
	 */
	public void custmInitializeFrm()
	{
	//	m_ctrlrObj.initializeCntrolr();
		
		MigLayout myGrid = new MigLayout(); 
		panel.setLayout(myGrid);
		panel.setLayout(new MigLayout());
		
	}
	 /**
	  * Method to handle sell button event
	  */
	private void selBtnHandlr()
	{
		myController.RemoveTower();
	}
	private void upgradeBtnHandlr()
	{
		myController.UpgradeSelectedTower();
	}

	
	
	
	/**
	 * Initialize the frame contents
	 */
	private void initialize() {
		m_frame = new JFrame();
		m_frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);		
		m_frame.setBounds(100, 100, 1081, 547);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel jPnlHdr = new JPanel();
		m_frame.getContentPane().add(jPnlHdr, BorderLayout.NORTH);
		jPnlHdr.setLayout(new BorderLayout(0, 0));
		
		JLabel lblGameHdr = new JLabel("Tower Defense Game");
		lblGameHdr.setFont(new Font("Arial Black", Font.BOLD, 34));
		lblGameHdr.setForeground(Color.RED);
		jPnlHdr.add(lblGameHdr, BorderLayout.WEST);
		
		JPanel pnlHdrSub = new JPanel();
		jPnlHdr.add(pnlHdrSub, BorderLayout.CENTER);
		
		JLabel lblAccLbl = new JLabel("\r\nAccount Balance : $");
		lblAccLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		pnlHdrSub.add(lblAccLbl);
		lblAccLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		m_lblAccBal.setFont(new Font("Arial", Font.PLAIN, 14));
		pnlHdrSub.add(m_lblAccBal);
		
		
		m_frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel jPnlFtr = new JPanel();
		m_frame.getContentPane().add(jPnlFtr, BorderLayout.SOUTH);
		jPnlFtr.setLayout(new BorderLayout(0, 0));
		
		JButton btnStrtGame = new JButton("Start Game");
		btnStrtGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	m_ctrlrObj.strtGameBtnHandlr();
			}
		});
		jPnlFtr.add(btnStrtGame, BorderLayout.EAST);
		
		JLabel lblArangFtr = new JLabel(" ");
		lblArangFtr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblArangFtr.setIcon(null);
		jPnlFtr.add(lblArangFtr, BorderLayout.SOUTH);
		
		JPanel jPnlDesc = new JPanel();
		m_frame.getContentPane().add(jPnlDesc, BorderLayout.EAST);
		jPnlDesc.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTwrTopLvl = new JPanel();
		pnlTwrTopLvl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Towers", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		jPnlDesc.add(pnlTwrTopLvl, BorderLayout.NORTH);
		
		final JLabel lblTwr1 = new JLabel("");
		lblTwr1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				myController.SetSelectedTower("lblTwr1");
				m_txtTwrDesc.setText(myController.ShowSelectedTowerDesc());
				//m_ctrlrObj.setTowerDesc("lblTwr1");
				//if(m_ctrlrObj.getSelectdTwr()!=null)
				//{
				//	selectedTower = m_ctrlrObj.getSelectdTwr();
				//} else {
			//		selectedTower = null;
			//	}
			}
		});
		lblTwr1.setIcon(new ImageIcon("C:\\Users\\Umer-PC\\Desktop\\SOEN PRJ\\TD-Game\\res\\tower3.png"));
		
		final JLabel lblTwr2 = new JLabel("");
		lblTwr2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//m_ctrlrObj.setTowerDesc("lblTwr2");
				myController.SetSelectedTower("lblTwr2");
				m_txtTwrDesc.setText(myController.ShowSelectedTowerDesc());

			}
		});
		lblTwr2.setIcon(new ImageIcon("C:\\Users\\Umer-PC\\Desktop\\SOEN PRJ\\TD-Game\\res\\tower.png"));
		pnlTwrTopLvl.setLayout(new BorderLayout(0, 0));
		pnlTwrTopLvl.add(lblTwr1, BorderLayout.WEST);
		pnlTwrTopLvl.add(lblTwr2, BorderLayout.EAST);
		
		JPanel pnlTwrSndLvl = new JPanel();
		pnlTwrTopLvl.add(pnlTwrSndLvl, BorderLayout.SOUTH);
		pnlTwrSndLvl.setLayout(new BorderLayout(0, 0));
		
		final JLabel lblTwr3 = new JLabel("");
		lblTwr3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//m_ctrlrObj.setTowerDesc("lblTwr3");
				myController.SetSelectedTower("lblTwr3");
				m_txtTwrDesc.setText(myController.ShowSelectedTowerDesc());


			}
		});
		lblTwr3.setHorizontalAlignment(SwingConstants.LEFT);
		pnlTwrSndLvl.add(lblTwr3, BorderLayout.WEST);
		lblTwr3.setIcon(new ImageIcon("C:\\Users\\Umer-PC\\Desktop\\SOEN PRJ\\TD-Game\\res\\tower4.png"));
		
		JPanel pnlTwrDesc = new JPanel();
		pnlTwrDesc.setBorder(new TitledBorder(null, "Tower Description", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		jPnlDesc.add(pnlTwrDesc, BorderLayout.CENTER);
		pnlTwrDesc.setLayout(new BorderLayout(0, 0));
		m_txtTwrDesc.setEditable(false);
		m_txtTwrDesc.setFont(new Font("Monospaced", Font.PLAIN, 18));
		
		pnlTwrDesc.add(m_txtTwrDesc);
		
		JLabel lblTwrSpcAdj = new JLabel("_____________");
		lblTwrSpcAdj.setForeground(Color.WHITE);
		lblTwrSpcAdj.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pnlTwrDesc.add(lblTwrSpcAdj, BorderLayout.SOUTH);
		
		JPanel pnlBtnColl = new JPanel();
		jPnlDesc.add(pnlBtnColl, BorderLayout.SOUTH);
		pnlBtnColl.setLayout(new BorderLayout(0, 0));
		
		JButton btnSellTwr = new JButton("Sell");
		btnSellTwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selBtnHandlr();
			}
		});
		pnlBtnColl.add(btnSellTwr, BorderLayout.NORTH);
		
		JButton btnUpgrdTwr = new JButton("Upgrade");
		btnUpgrdTwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upgradeBtnHandlr();
			}
		});
		pnlBtnColl.add(btnUpgrdTwr, BorderLayout.CENTER);
		
		JLabel lblBtnCollSpcAdj = new JLabel(" ");
		lblBtnCollSpcAdj.setFont(new Font("Tahoma", Font.PLAIN, 35));
		pnlBtnColl.add(lblBtnCollSpcAdj, BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		m_frame.setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mntmOpenMap = new JMenuItem("Open Map");
		mntmOpenMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				// read a file from disk
				
				try
				{

					myController.OpenMap();
					myController.DrawMap(true, panel);

					panel.revalidate();
					panel.repaint();			    		


				}catch(Exception ioe){
					ioe.printStackTrace();
					return;
				}
			}
			
		});
		mnGame.add(mntmOpenMap);
	}
	
	
}
