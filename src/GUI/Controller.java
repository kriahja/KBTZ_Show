/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author a.tamas
 */
import javax.swing.Timer;
public class Controller
{

    public static void main(String[] args)
    {
        final NewScrollPanel r1 = NewScrollPanel.getInstance();

        final Thread t1 = new Thread(r1);

        t1.start();
        
       

        int delay = 10000;

        ActionListener taskPerformer = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                t1.interrupt();
                r1.setVisible(false);
            }
        };
        
        new Timer(delay, taskPerformer).start();

    }

}
