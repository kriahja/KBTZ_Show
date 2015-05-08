/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.TextManager;
import BE.Text;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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
    
    int dispId;

    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = 70;
    int y = (int) screensize.getHeight();

    private ArrayList<Text> txt;
    TextManager tMgr;
    int i = 0;
    int counter = 3;

    private TextPanel(int dispId)
    {
        System.out.println(x + "  " + y);
        tMgr = TextManager.getInstance();
        txt = tMgr.readCurrent(dispId);
        this.dispId = dispId;
    }

    public static TextPanel getInsatnce(int dispId)
    {
        if (instance == null) {
            instance = new TextPanel(dispId);
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

        if (c.get(Calendar.HOUR) == 0 && c.get(Calendar.MINUTE) == 0) {
            txt = tMgr.readCurrent(dispId);
        }

        int nrOfLines = 1;
        super.paint(g);
        super.setBackground(Color.WHITE);

        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Arial", Font.PLAIN, 80);

        g2.setFont(font);
        String txtt = txt.get(i).getText();
       // txtt = txtt.replace(" ", "\n");

        //g2.drawString(txtt, x, y);
        g2.setColor(Color.BLACK);

        nrOfLines = drawStringMultiLine(g2, txtt, screensize.width - 100, x, y);

        if (counter == 0) {
            y -= 1;
            counter = 3;
        }
        // x = getWidth() / getWidth() / 2;
        if (y + nrOfLines * 200 < 1) {
            y = screensize.height;

            if (i < txt.size()) {
                ++i;
            }
            if (i == txt.size()) {
                i = 0;
            }

        }
        if (counter != 0) {
            --counter;
        }
        repaint();
    }

    public static int drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y)
    {
        int nrOfLines = 1;
        FontMetrics m = g.getFontMetrics();
        if (m.stringWidth(text) < lineWidth) {
            g.drawString(text, x, y);
        } else {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for (int i = 1; i < words.length; i++) {
                if (m.stringWidth(currentLine + words[i]) < lineWidth) {
                    currentLine += " " + words[i];

                } else {
                    g.drawString(currentLine, x, y);
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            ++nrOfLines;
            if (currentLine.trim().length() > 0) {
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
