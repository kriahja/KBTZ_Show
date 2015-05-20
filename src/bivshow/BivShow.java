/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bivshow;

import GUI.BivMain;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author notandi
 */
public class BivShow
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {     
              new BivMain().setVisible(true);
            }
        });
    }
    private static void sleepThread() {
        try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException ex)
            {
                // Do something, if there is a exception
                System.out.println(ex.toString());
            } 
    }
    
}
