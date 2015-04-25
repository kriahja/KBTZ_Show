/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Zalan
 */
public class ScrollingPanel extends JPanel {

    private int x = 0;
    private final int y = 100;
   


    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        Font font = new Font("Tahoma",Font.BOLD+Font.PLAIN,100);
        g2.setFont(font);
        g2.setColor(Color.red);
        g2.drawString("Scrolling TExt",x,y);
        
        try{Thread.sleep(100);}catch(Exception ex){}
        x+=10;
        if(x>this.getWidth ())
        {
            x=0;
        } 
        repaint();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
     
        System.out.println("why dont want to start this guy?");
  }
    

}
