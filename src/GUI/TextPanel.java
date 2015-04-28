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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;

/**
 *
 * @author Zalan
 */
public class TextPanel extends JPanel
{

    private static TextPanel instance = null;

    private int x = 50;
    private int y = 800;
    private ArrayList<Text> txt;
    TextManager tMgr;
    int i = 0;
    int counter = 3;

    private TextPanel()
    {
        tMgr = TextManager.getInstance();
        txt = tMgr.readCurrent();

    }

    public static TextPanel getInsatnce()
    {
        if (instance == null)
        {
            instance = new TextPanel();
        }
        return instance;
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
        Calendar c = Calendar.getInstance();

        if (c.get(Calendar.HOUR) == 0 && c.get(Calendar.MINUTE) == 0)
        {
            txt = tMgr.readCurrent();
        }

        int nrOfLines = 1;
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Helvetica", Font.PLAIN, 100);

        g2.setFont(font);
        String txtt = txt.get(i).getText();
       // txtt = txtt.replace(" ", "\n");

        //g2.drawString(txtt, x, y);
        g2.setColor(Color.BLACK);

        nrOfLines = drawStringMultiLine(g2, txtt, 1300, x, y);

        if (counter == 0)
        {
            y -= 1;
            counter = 1;
        }
        // x = getWidth() / getWidth() / 2;
        if (y + nrOfLines * 200 < 1)
        {
            y = 800;

            if (i < txt.size())
            {
                ++i;
            }
            if (i == txt.size())
            {
                i = 0;
            }

        }
        if (counter != 0)
        {
            --counter;
        }
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

    public static int drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y)
    {
        int nrOfLines = 1;
        FontMetrics m = g.getFontMetrics();
        if (m.stringWidth(text) < lineWidth)
        {
            g.drawString(text, x, y);
        }
        else
        {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for (int i = 1; i < words.length; i++)
            {
                if (m.stringWidth(currentLine + words[i]) < lineWidth)
                {
                    currentLine += " " + words[i];

                }
                else
                {
                    g.drawString(currentLine, x, y);
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            ++nrOfLines;
            if (currentLine.trim().length() > 0)
            {
                g.drawString(currentLine, x, y);
            }
        }
        return nrOfLines;
    }

    public int getId()
    {
        return i;
    }

}
