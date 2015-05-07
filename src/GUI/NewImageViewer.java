/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.ImageManager;
import BE.Image;
import java.awt.Dimension;
import java.awt.Toolkit; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author notandi
 */
public class NewImageViewer extends JFrame implements Runnable
{

    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screensize.getWidth();
    int height = (int) screensize.getHeight();
    

    private static NewImageViewer instance = null;
    ImageManager iMgr;
    ArrayList<Image> imgList;
    ArrayList<String> subfolders = new ArrayList<>();

    
    private List<BufferedImage> images;
    private int currentPic = 0;

    private String path = new String("C:/Info/images/");

    static final String[] EXTENSIONS = new String[]
    {
        "gif", "png", "jpg" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter()
    {

        @Override
        public boolean accept(final File dir, final String name)
        {
            for (final String ext : EXTENSIONS)
            {
                if (name.endsWith("." + ext))
                {
                    return (true);
                }
            }
            return (false);
        }
    };

    /**
     * Creates new form NewImageViewer
     */
    private NewImageViewer()
    {
        iMgr = ImageManager.getInstance();
        imgList = iMgr.readCurrent();
        initComponents();
        
        System.out.println(imgList);
  
        for (int i = 0; i < imgList.size(); ++i)
        {
            subfolders.add(i, imgList.get(i).getPath());
        }
        
       // final File[] files = new File("C:/Info/images/" + subfolders.get(index)).listFiles();
        System.out.println(subfolders.get(1));
        File[] files = null;
        
        File[][] filess = null;
       
        ArrayList<File> fille;
        fille = new ArrayList<>() ;
        
            
        
        for(int i = 0; i < subfolders.size(); ++i)
        {
            
            
            files = new File("C:/Info/images/" + subfolders.get(i)).listFiles();
            
            
            for(int j = 0; j < files.length; ++j)
            {
                fille.add(files[j]);
            }
            System.out.println(fille.toString());
        }
        
          
        
        TimerTask change = new TimerTask() {
            
            @Override
            public void run()
            {
                switchPic.doClick();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(change, 5000, 5000);
        
       
        images = new ArrayList<>();
        for(int i = 0; i < fille.size(); ++i)
            {
            File file = fille.get(i);
                System.out.println(file.toString());
            
         //    path +  "/" +subfolders.get(0) +
          
            if (file.isFile())
            {
                try
                {
                    images.add(ImageIO.read(new File(file.toString())));
                    
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            
        }

        switchPic.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentPic++;
                if (currentPic >= images.size())
                {
                    currentPic = 0;
                }
                label.setIcon(new ImageIcon(images.get(currentPic).getScaledInstance(width, height, width)));
            }
        });

        FrameCtrl();
    }

    public static NewImageViewer getInstance()
    {
        if (instance == null)
        {
            instance = new NewImageViewer();
        }
        return instance;
    }

    private void FrameCtrl()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        switchPic.setOpaque(false);
        switchPic.setContentAreaFilled(false);
        switchPic.setBorderPainted(false);
        switchPic.doClick();

        pack();
        setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        switchPic = new javax.swing.JButton();
        label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        switchPic.setBorder(null);
        switchPic.setMaximumSize(new java.awt.Dimension(27910, 27910));
        switchPic.setMinimumSize(new java.awt.Dimension(0, 0));
        switchPic.setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().add(switchPic, java.awt.BorderLayout.SOUTH);

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(label, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(NewImageViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(NewImageViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(NewImageViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(NewImageViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                NewImageViewer.getInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    private javax.swing.JButton switchPic;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run()
    {
                NewImageViewer.getInstance().setVisible(true);
    }
}
