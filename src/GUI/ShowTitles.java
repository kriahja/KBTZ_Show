package GUI;

import BLL.TextManager;
import BE.Text;
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
    
    
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
  
    int x = (int) screensize.getWidth();
    
    /**
     *
     * @param dispId
     */
    public ShowTitles(int dispId)
   {
       tp  = TextPanel.getInsatnce(dispId);
       tMgr = TextManager.getInstance();
       txt = tMgr.readCurrent(dispId);
      
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
       
       
       
       
       repaint();
   }
   
    
}