/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.Image;
import BE.Text;
import BLL.ImageManager;
import BLL.TextManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author a.tamas
 */
import javax.swing.Timer;

public class Controller
{

    Timer timer;
    int delay = 10000;
    NewScrollPanel r1;
    NewImageViewer r2;

    int counter = 0;
    int imgp = -1;
    boolean change = false;

    TextManager tMgr;
    ArrayList<Text> txtList = new ArrayList<>();

    ImageManager iMgr;
    ArrayList<Image> imgList = new ArrayList<>();

    public ActionListener taskPerformer = new ActionListener()
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if(counter % 2 == 0)
            {    imgp *= -1;
                change = true;
            }
           
            
            if (change == true) {
                if (imgp == -1) {
                    r1.setVisible(false);
                    r2.setVisible(true);
                } else {
                    r1.setVisible(true);
                    r2.setVisible(false);
                }
                change = false;
            }
            ++counter;
            if (counter == 100) {
                counter = 0;
            }

        }
    };

    /**
     *
     * @param dispId
     */
    public Controller(final int dispId)
    {
        Thread thread = new Thread()
        {
            public void run()
            {
                r1 = NewScrollPanel.getInstance(dispId);
                r1.setVisible(true);
            }
        };
        Thread thread2 = new Thread()
        {
            public void run()
            {
                r2 = NewImageViewer.getInstance(dispId);
                timer.start();
            }
        };
        thread.start();
        thread2.start();
        scheduling();
    }

    public static void main(String[] args, int disp)
    {
        System.out.println("before");
        new Controller(disp);
        System.out.println("main");

    }

    public void scheduling()
    {
        timer = new Timer(delay, taskPerformer);
        timer.setInitialDelay(5000);

    }

    /**
    *@return getTimer from NewScrollPanel
    **/
    public double getTextTimer()
    {
        System.out.println(r1.getTimer());
        return r1.getTimer();
    }

}