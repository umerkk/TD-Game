package code.game.Controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import code.game.Map.*;
import code.game.towers.TowerModel;
/**
 * @author Umer-PC
 *
 */
public class GameController {

	private GameMap map=null;
	private int accountBalance=500;
	private TowerModel selectedTower=null;
	private Boolean newTowerSelected;
	

//======================================================================================
	public int GetAccountBalance()
	{
		return accountBalance;
	}
	
	public void SetAccountBalance(int money)
	{
		this.accountBalance = money;
	}
	
	public void AddMoneyToAccount(int money)
	{
		this.accountBalance+=money;
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
				map = new GameMap("myMap", mapArray);

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

	private void DrawMap(boolean isExisting, Panel parentPanel)
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
					parentPanel.add(temp, "width 80, height 80" + _append);					
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

	/**
	 * Method to handle click event of map cell
	 * @param e mouse event
	 * @param cell cell to which event block 
	 */

	public void click(MouseEvent e, JPanel cell) 
	{
		boolean overideExisting=false;
		String tempName = cell.getName();
		char[] name_exploded = tempName.toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		//1=StartPoint, 9999=End, 2=Path, 3=Delete
		selectedCell = cell;

		if(!m_ctrlrObj.checkCellExist(tempName))
			DrawTower(cell);
		else
		{
			TowerModel tmpmdl = m_ctrlrObj.getTower(tempName);
			m_ctrlrObj.setSelectedTower(tmpmdl, false);
		}

	}


}
