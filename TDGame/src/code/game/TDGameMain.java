package code.game;

import java.awt.EventQueue;

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

import code.game.towers.TowerModel;

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
 * @version 1.0.0.0
 */

public class TDGameMain implements Observer {

	private JFrame m_frame;
	private GameController m_ctrlrObj;
	JTextArea m_txtTwrDesc = new JTextArea();
	JLabel m_lblAccBal = new JLabel("0  ");
	
	
	int[][] mapArray;
	ScrollPane sc_panel = new ScrollPane();
	//Panel panel_1 = new Panel();
	Panel panel = new Panel();
	int ArrayRow;
	int ArrayCol;
	int selectedTool = 0;
	
	JPanel selectedCell;
	TowerModel selectedTower;
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		m_lblAccBal.setText(String.valueOf(((GameController)arg0).getAccountBalnc()));
		m_txtTwrDesc.setText(null);
		TowerModel towrdesc = ((GameController)arg0).getSelectdTwr();
		if(towrdesc == null)
			return;
		m_txtTwrDesc.append(towrdesc.getTowerDetails().toString());
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameController gamecntrlrobj =new GameController();
					TDGameMain window = new TDGameMain(gamecntrlrobj);
					gamecntrlrobj.addObserver(window);
					window.custmInitializeFrm();
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
	public TDGameMain(GameController gamcntrlrobj) {
		m_ctrlrObj = gamcntrlrobj;
		
		initialize();
		
		custmInitializeFrm();
	}
	
	/**
	 * Initialize the frame
	 */
	public void custmInitializeFrm()
	{
		m_ctrlrObj.initializeCntrolr();
		
		MigLayout myGrid = new MigLayout(); 
		panel.setLayout(myGrid);
		panel.setLayout(new MigLayout());
		
	}
	
	private void selBtnHandlr()
	{
		// code to update ui
		//m_ctrlrObj.setTowerDesc("lblTwr1");
		m_ctrlrObj.sellTower(selectedTower);
	
	}
	
	private void DrawMapItem(int type, JPanel cell)
	{
		
		  JLabel t = new JLabel();
		  t.setForeground(Color.WHITE);
		  t.setFont(new Font("Arial",0,20));
		      
		      if(type==1)
		      {
		    	  t.setText("S");
		    	  cell.setBackground(Color.blue);
		    	
		      } else  if(type==9999)
		      
		      {
		    	  t.setText("X");
		    	  cell.setBackground(Color.red);
		    	
		      } else  if(!(type==0)){
		    	  t.setText("P");
		    	  cell.setBackground(Color.green);
		      }
		      
		      
		      
		      
		      t.setBounds(20, 20, 50, 50);
	          cell.add(t);
	}
	
	private void DrawTower(JPanel cell)
	{
		
		  String tempName = cell.getName();
	      char[] name_exploded = tempName.toCharArray();
	      int x = Integer.parseInt(String.valueOf(name_exploded[0]));
	      int y = Integer.parseInt(String.valueOf(name_exploded[1]));
	      
		  JLabel t=null;
		 
		  TowerModel m = selectedTower;
		  if(m!=null)
		  {
		
		if(mapArray[x][y] == 0) { 
		  
		  
		  if(m.getName().equals("Castle Tower"))
		  {
			 try {
				 BufferedImage myPicture = ImageIO.read(new File("TDGame/src/images/tower4.png"));
			 
			  t = new JLabel(new ImageIcon(myPicture));
			 } catch (Exception e){}
			  
			
		  } else if(m.getName().equals("Imperial Tower"))
		  {
			  try {
					 BufferedImage myPicture = ImageIO.read(new File("TDGame/src/images/tower4.png"));
				 
				  t = new JLabel(new ImageIcon(myPicture));
				 } catch (Exception e){}
		  }else if(m.getName().equals("Industrial Tower"))
		  {
			  try {
					 BufferedImage myPicture = ImageIO.read(new File("TDGame/src/images/tower4.png"));
				 
				  t = new JLabel(new ImageIcon(myPicture));
				 } catch (Exception e){}
		  }
		   cell.setBackground(Color.blue);
		    m_ctrlrObj.setAccBalnc(m_ctrlrObj.getAccountBalnc() - m.getCostOfTower());
		    	  m_ctrlrObj.ResetTowerData();
		      t.setBounds(0, 0, 80, 80);
	          cell.add(t);
	          
		  }
		  } else {
			  
			  selectedCell = cell;
			  m_ctrlrObj.m_selctdtower = m;
			  
			  
		  }
	}
	
	
	private void DrawMap(int[][] mapArray, boolean isExisting, Panel parentPanel)
	{
		if(isExisting)
		{
		
			for(int k=0;k<ArrayRow;k++)
			{
				for(int i=0;i<ArrayCol;i++)
				{
				
					
					String _append = "";
					if(i==ArrayCol-1)
					{
						_append = ", wrap";
					} else
					{
						
					}
					JPanel temp = new JPanel();
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
					if(mapArray[k][i]==1)
				      {
				   
				      
				    	  DrawMapItem(1, temp);
				    	 
				    	 
				      }  else if(mapArray[k][i]==9999)
				      
				      {
				    	  DrawMapItem(9999, temp);
				    	
				    
				      } else if(mapArray[k][i]==0)
					      
				      {
				    	  DrawMapItem(0, temp);
				    	 
				      } else
				      
					      
				      {
				    	  DrawMapItem(2,temp);
				    	
				    	
				    	
				      }
				     
					
					parentPanel.add(temp, "width 80, height 80" + _append);
					
				//	panelsHolder[k][i] = temp;
					
				}
				      
				      
				}
				
			}
			
			
			
		
		else
		{
			for(int k=0;k<ArrayRow;k++)
			{
				for(int i=0;i<ArrayCol;i++)
				{
					String _append = "";
					if(i==ArrayCol-1)
					{
						_append = ", wrap";
					} else
					{
						
					}
					JPanel temp = new JPanel();
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
					
					parentPanel.add(temp, "width 80, height 80" + _append);
					
					
					
				}
			}
		}
	}
		
	public void click(MouseEvent e, JPanel cell) {
		  
		boolean overideExisting=false;
      String tempName = cell.getName();
      char[] name_exploded = tempName.toCharArray();
      int x = Integer.parseInt(String.valueOf(name_exploded[0]));
      int y = Integer.parseInt(String.valueOf(name_exploded[1]));
    //1=StartPoint, 9999=End, 2=Path, 3=Delete
      
    	DrawTower(cell);
    	
      
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
		
		final JLabel lblTwr1 = new JLabel("");
		lblTwr1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				m_ctrlrObj.setTowerDesc("lblTwr1");
				if(m_ctrlrObj.getSelectdTwr()!=null)
				{
					selectedTower = m_ctrlrObj.getSelectdTwr();
				} else {
					selectedTower = null;
				}
			}
		});
		lblTwr1.setIcon(new ImageIcon(TDGameMain.class.getResource("/images/tower3.png")));
		
		final JLabel lblTwr2 = new JLabel("");
		lblTwr2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m_ctrlrObj.setTowerDesc("lblTwr2");
			}
		});
		lblTwr2.setIcon(new ImageIcon(TDGameMain.class.getResource("/images/tower4.png")));
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
				m_ctrlrObj.setTowerDesc("lblTwr3");
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
				selBtnHandlr();
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
		
		JMenuBar menuBar = new JMenuBar();
		m_frame.setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mntmOpenMap = new JMenuItem("Open Map");
		mntmOpenMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				// read a file from disk
				
				JFileChooser filebrwsr = new JFileChooser();
				
				int result = filebrwsr.showOpenDialog(m_frame);
				if (result == JFileChooser.APPROVE_OPTION) 
				{
					
				
					  
			        try
			        {
			        	File selectedFile = filebrwsr.getSelectedFile();
			            FileInputStream fis = new FileInputStream(selectedFile);
			            ObjectInputStream ois = new ObjectInputStream(fis);
			            mapArray = (int[][]) ois.readObject();
			            ois.close();
			            fis.close();
			           

			    		ArrayRow = mapArray.length;
			    		ArrayCol = mapArray[0].length;
			    		
			           DrawMap(mapArray, true, panel);
			           panel.revalidate();
			           panel.repaint();			    		
			            
			            
			         }catch(IOException ioe){
			             ioe.printStackTrace();
			             return;
			          }catch(ClassNotFoundException c){
			             System.out.println("Class not found");
			             c.printStackTrace();
			             return;
			          }
			       
				    
				    
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select a map file.", "Warning: File not selected.", JOptionPane.WARNING_MESSAGE);	
				}
			
			}
			
		});
		mnGame.add(mntmOpenMap);
	}
	
	
}
