/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ActionListener taskPerformer = new ActionListener()
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
            if (counter % 2 == 0) {
                imgp *= -1;
                change = true;
            }
            if (change == true) {
                if (imgp == -1) {
                    r1.setVisible(true);
                    r2.setVisible(false);
                } else {
                    r1.setVisible(false);
                    r2.setVisible(true);
                }
                change = false;
            }
            
            ++counter;
            System.out.println(counter);
        }
    };

    public Controller()
    {
        r1 = NewScrollPanel.getInstance();

        r2 = NewImageViewer.getInstance(0);

        scheduling();
    }

    public static void main(String[] args)
    {
        System.out.println("before");
        new Controller();
        System.out.println("main");

    }

    public void scheduling()
    {
        timer = new Timer(delay, taskPerformer);

        timer.start();
        System.out.println("sched");

    }

}
