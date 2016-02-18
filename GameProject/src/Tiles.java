
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
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
public class Tiles extends JButton implements Serializable{
    private int rowIndex;
    private int colIndex;
    private String state;
    private MapEditor mapEditor;
    private int tileNo;
    
    public Tiles(int x, int y, int n,MapEditor me){
        
       rowIndex=x;
       colIndex=y;
       state="scenary";
       mapEditor=me;
       tileNo=n;
           this.setBackground(new Color(102,255,0));
       this.setBorder(new LineBorder(Color.black,1,false));
       this.setText("");
       this.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
          int tool=mapEditor.getTool();
         
          if(tool==1){
            changeDesign(new Color(204,102,0),"S","start"); 
          }
          else if(tool==2){
           changeDesign(new Color(204,102,0),"E","end");   
          }
          else if(tool==3){
           changeDesign(new Color(204,102,0),"","path");   
          }
          else if(tool==4){
           changeDesign(new Color(102,255,0),"","scenary");   
          }
      
       }  
       
       });
    }
    
    public void changeDesign(Color c, String text,String State){
        
        this.setBackground(c);
        this.setText(text);
        this.setHorizontalAlignment(CENTER);
        state=State;
        this.revalidate();
    }
    public String getState(){
        return state;
    }
    public int getRow(){
        return rowIndex;
    }
    
    public int getCol(){
        return colIndex;
    }
    public int getTileNo(){
        return tileNo;
    }
    
   public static void main(String[]args){
     JFrame f=new JFrame();
     f.setVisible(true);
     JPanel p=new JPanel();
     p.setLayout(new GridLayout(2,2,2,2));
     p.setBackground(Color.yellow);
     f.add(p);
     p.add(new Tiles(1,1,3,new MapEditor()));
   } 
    
}
