package code.game;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;


/**
 * This is the view class for Tower Defance Game play.
 * 
 * @author Alaa
 * @author lokesh
 * @version 1.0.0.0
 */

public class TDGameMain implements Observer {

	private JFrame m_frame;
	private GameController m_ctrlrObj;
	JTextArea m_txtTwrDesc = new JTextArea();
	JLabel m_lblAccBal = new JLabel("0  ");
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		m_lblAccBal.setText(String.valueOf(((GameController)arg0).getAccountBalnc()));
		m_txtTwrDesc.setText(null);
		String towrdesc = ((GameController)arg0).getSelectdTwrDtls().toString();
		if(towrdesc == null)
			return;
		m_txtTwrDesc.append(towrdesc);
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TDGameMain window = new TDGameMain();
					window.m_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TDGameMain() {
		initialize();
		
		custmInitializeFrm();
	}
	
	/**
	 * 
	 */
	private void custmInitializeFrm()
	{
		try
		{
			m_ctrlrObj.initializeCntrolr(this);
			Component[] components = m_frame.getContentPane().getComponents();
			MigLayout myGrid = new MigLayout();
			
			for (int i=0; i < components.length; i++) 
			{
	            if(components[i].getName().equals("jPnlMain"))
	            {
	            	((JPanel)components[i]).setLayout(myGrid);
	            	((JPanel)components[i]).setLayout(new MigLayout());
	            }
	           
	            //componentMap.put(components[i].getName(), components[i]);
			}
			
			
		}
		catch(Exception ex){}
	}
	
	/**
	 * Method to handle the load map button 
	 */
	public void loadMapBtnHandlr()
	{
		// read a file from disk
		File selectedFile;
		JFileChooser filebrwsr = new JFileChooser();
		filebrwsr.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = filebrwsr.showOpenDialog(m_frame);
		if (result == JFileChooser.APPROVE_OPTION) 
		{
		    selectedFile = filebrwsr.getSelectedFile();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please select a map file.", "Warning: File not selected.", JOptionPane.WARNING_MESSAGE);	
		}
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		m_frame = new JFrame();
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
		
		JButton btnLoadMap = new JButton("Load Map");
		btnLoadMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadMapBtnHandlr();
			}
		});
		jPnlHdr.add(btnLoadMap, BorderLayout.EAST);
		
		JPanel pnlHdrSub = new JPanel();
		jPnlHdr.add(pnlHdrSub, BorderLayout.CENTER);
		
		JLabel lblAccLbl = new JLabel("\r\nAccount Balance : $");
		lblAccLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		pnlHdrSub.add(lblAccLbl);
		lblAccLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		m_lblAccBal.setFont(new Font("Arial", Font.PLAIN, 14));
		pnlHdrSub.add(m_lblAccBal);
		
		JPanel jPnlMain = new JPanel();
		m_frame.getContentPane().add(jPnlMain, BorderLayout.CENTER);
		
		JPanel jPnlFtr = new JPanel();
		m_frame.getContentPane().add(jPnlFtr, BorderLayout.SOUTH);
		jPnlFtr.setLayout(new BorderLayout(0, 0));
		
		JButton btnStrtGame = new JButton("Start Game");
		btnStrtGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_ctrlrObj.strtGameBtnHandlr();
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
		
		JLabel lblTwr1 = new JLabel("");
		lblTwr1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				m_ctrlrObj.setTowerDesc(lblTwr1.getName());
			}
		});
		lblTwr1.setIcon(new ImageIcon(TDGameMain.class.getResource("/images/tower3.png")));
		
		JLabel lblTwr2 = new JLabel("");
		lblTwr2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m_ctrlrObj.setTowerDesc(lblTwr2.getName());
			}
		});
		lblTwr2.setIcon(new ImageIcon(TDGameMain.class.getResource("/images/tower4.png")));
		pnlTwrTopLvl.setLayout(new BorderLayout(0, 0));
		pnlTwrTopLvl.add(lblTwr1, BorderLayout.WEST);
		pnlTwrTopLvl.add(lblTwr2, BorderLayout.EAST);
		
		JPanel pnlTwrSndLvl = new JPanel();
		pnlTwrTopLvl.add(pnlTwrSndLvl, BorderLayout.SOUTH);
		pnlTwrSndLvl.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTwr3 = new JLabel("");
		lblTwr3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m_ctrlrObj.setTowerDesc(lblTwr3.getName());
			}
		});
		lblTwr3.setHorizontalAlignment(SwingConstants.LEFT);
		pnlTwrSndLvl.add(lblTwr3, BorderLayout.WEST);
		lblTwr3.setIcon(new ImageIcon(TDGameMain.class.getResource("/images/tower.png")));
		
		JPanel pnlTwrDesc = new JPanel();
		pnlTwrDesc.setBorder(new TitledBorder(null, "Tower Description", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		jPnlDesc.add(pnlTwrDesc, BorderLayout.CENTER);
		pnlTwrDesc.setLayout(new BorderLayout(0, 0));
		
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
				m_ctrlrObj.selBtnHandlr();
			}
		});
		pnlBtnColl.add(btnSellTwr, BorderLayout.NORTH);
		
		JButton btnUpgrdTwr = new JButton("Upgrade");
		btnUpgrdTwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_ctrlrObj.upgrdBtnHandlr();
			}
		});
		pnlBtnColl.add(btnUpgrdTwr, BorderLayout.CENTER);
		
		JLabel lblBtnCollSpcAdj = new JLabel(" ");
		lblBtnCollSpcAdj.setFont(new Font("Tahoma", Font.PLAIN, 35));
		pnlBtnColl.add(lblBtnCollSpcAdj, BorderLayout.SOUTH);
	}
	
}
