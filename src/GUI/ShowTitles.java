/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.TextManager;
import BE.Text;
import BLL.DisplayManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author notandi
 */
public class ShowTitles extends JPanel
{
    TextPanel tp;
    TextManager tMgr;
    private ArrayList<Text> txt;
    DisplayManager dMgr;
    int dispId;
    
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
  
    int x = (int) screensize.getWidth();
    
    
   public ShowTitles(int dispId)
   {
       tp  = TextPanel.getInsatnce(dispId);
       tMgr = TextManager.getInstance();
       txt = tMgr.readCurrent(dispId);
       dMgr = DisplayManager.getInstance();
       this.dispId = dispId;
      
   } 
   @Override
   public void paint(Graphics g)
   {
       int i = tp.getId();
       
       super.paint(g); 
       super.setBackground(Color.white);
       
       Graphics2D j = (Graphics2D) g;
       Font font = new  Font( Font.SERIF, Font.PLAIN, 80);
       j.setColor(Color.GRAY);
       
       j.setFont(font);
       String title = txt.get(i).getTitle();
       j.drawString(title, x / 3, 80);
       
       if(dMgr.toBeReloadedText() == true)
            {
                i = tp.getId();
                txt = tMgr.readCurrent(dispId);
                dMgr.reloadText(false);
                
            }
       
       
       repaint();
   }
   
    
}
