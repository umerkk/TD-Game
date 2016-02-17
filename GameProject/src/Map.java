

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alaa
 */
public class Map extends JPanel{
    private int width;
    private int height;
    private ArrayList<Tiles> map;
    
    
    public Map(int w, int h)
    {
        map=new ArrayList<Tiles>();
        width=w;
        height=h;
        this.setBackground(new Color(102,255,0));
        this.setSize(width, height);
        this.setBorder(new LineBorder(Color.BLACK,5));   
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        int row=map.get(map.size()-1).getRow();
        int col=map.get(map.size()-1).getCol();
        row++;
        col++;
        int rectWidth=(int)((width-10)/col);
        int rectHeight=(int)((height-10)/row);
        int w=rectWidth*col+10;
        int h=rectHeight*row+10;
        this.setSize(w, h);
        Color lineColor=new Color(102,195,0);
        Color pathColor=new Color(204,102,0);
        g2d.setColor(lineColor);
        for(int i=1;i<row;i++)
        {
            g2d.drawLine(5, i*rectHeight+5, w-6, i*rectHeight+5);
        }
        for(int i=1;i<col;i++)
        {
            g2d.drawLine(i*rectWidth+5, 5, i*rectWidth+5, h-6);
        }
        g2d.setColor(pathColor);
        for(int i=0;i<map.size();i++)
        {
            if(map.get(i).getState().equals("start") || map.get(i).getState().equals("end") || map.get(i).getState().equals("path"))
            {
                int x=getX(map.get(i), rectWidth);
                int y=getY(map.get(i), rectHeight);
                g2d.fillRect(x, y, rectWidth, rectHeight);
                
            }
        }
        
        
    }
    
    private int getX(Tiles t, int w)
    {
        return t.getCol()*w+5;
    }
    
    private int getY(Tiles t, int h)
    {
        return t.getRow()*h+5;
    }
    
    public void setMap(ArrayList<Tiles> m)
    {
        map=m;
    }
    
}
