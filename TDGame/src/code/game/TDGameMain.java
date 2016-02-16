package code.game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
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


/**
 * This is the view class for Tower Defance Game play.
 * 
 * @author Alaa
 * @author lokesh
 * @version 1.0.0.0
 */

public class TDGameMain {

	private JFrame m_frame;
	private GameController m_ctrlrObj;

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
		
		JLabel lblNewLabel = new JLabel("Tower Defense Game");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 34));
		lblNewLabel.setForeground(Color.RED);
		jPnlHdr.add(lblNewLabel, BorderLayout.WEST);
		
		JButton btnLoadMap = new JButton("Load Map");
		btnLoadMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_ctrlrObj.loadFileBtnHandlr();
			}
		});
		jPnlHdr.add(btnLoadMap, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		jPnlHdr.add(panel, BorderLayout.CENTER);
		
		JLabel lblAccLbl = new JLabel("\r\nAccount : $");
		lblAccLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblAccLbl);
		lblAccLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAccBal = new JLabel("0  ");
		lblAccBal.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblAccBal);
		
		JLabel lblGameLvlLbl = new JLabel("  Level :");
		lblGameLvlLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblGameLvlLbl);
		
		JLabel lblGameLbl = new JLabel("1");
		lblGameLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblGameLbl);
		
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
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setIcon(null);
		jPnlFtr.add(lblNewLabel_2, BorderLayout.SOUTH);
		
		JPanel jPnlDesc = new JPanel();
		m_frame.getContentPane().add(jPnlDesc, BorderLayout.EAST);
		jPnlDesc.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Towers", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		jPnlDesc.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TDGameMain.class.getResource("/images/tower3.png")));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TDGameMain.class.getResource("/images/tower4.png")));
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(lblNewLabel_3, BorderLayout.WEST);
		panel_1.add(lblNewLabel_4, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel_1, BorderLayout.WEST);
		lblNewLabel_1.setIcon(new ImageIcon(TDGameMain.class.getResource("/images/tower.png")));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Tower Description", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		jPnlDesc.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JTextArea txtrAbcdefghijklDfbhkdfjhdll = new JTextArea();
		panel_3.add(txtrAbcdefghijklDfbhkdfjhdll);
		
		JLabel lblNewLabel_6 = new JLabel("_____________");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblNewLabel_6, BorderLayout.SOUTH);
		
		JPanel panel_4 = new JPanel();
		jPnlDesc.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Sell");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_ctrlrObj.selBtnHandlr();
			}
		});
		panel_4.add(btnNewButton, BorderLayout.NORTH);
		
		JButton btnNewButton_1 = new JButton("Upgrade");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_ctrlrObj.upgrdBtnHandlr();
			}
		});
		panel_4.add(btnNewButton_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_5 = new JLabel(" ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 35));
		panel_4.add(lblNewLabel_5, BorderLayout.SOUTH);
	}

}
