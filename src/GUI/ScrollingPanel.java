/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.TextManager;
import Entities.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Zalan
 */
public class ScrollingPanel extends JPanel
{

    private int x;
    private int y;
    private ArrayList<Text> txt;
    TextManager tMgr;
    int i = 0;

    public ScrollingPanel()
    {
        tMgr = TextManager.getInstance();
        txt = tMgr.readCurrent();
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Tahoma", Font.BOLD + Font.PLAIN, 100);
        
        g2.setFont(font);
        g2.setColor(Color.red);
        

        g2.drawString(txt.get(i).getText(), x, y);
        
        

        try {
            Thread.sleep(5);
        } catch (Exception ex) {
        }
        x += 1;
        y = getHeight() / 2;
        if (x > this.getWidth()) {
            x = 0;
            if (i < txt.size()) {
                ++i;
            }
            if (i == txt.size()) {
                i = 0;
            }
        }
//         try{Thread.sleep(5);}catch(Exception ex){}
//        y+=1;
//        x = getWidth()/getWidth()/2;
//        if(x>this.getHeight())
//        {
//            x=0;
//        } 

        repaint();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {

        System.out.println("why dont want to start this guy?");
    }

}
