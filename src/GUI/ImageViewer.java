package GUI;

import BLL.ImageManager;
import Entities.Image;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ImageViewer
{

    ImageManager iMgr;
    ArrayList<Image> imgList;
    ArrayList<String> subfolders = new ArrayList<>();

    public static void main(String[] args)
    {

    }

    
    private String path = new String("C:/info/images/");

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "gif", "png", "jpg" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter()
    {

        @Override
        public boolean accept(final File dir, final String name)
        {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    private JLabel label;

    private List<BufferedImage> images;
    private int currentPic = 0;

    public ImageViewer(final int index)
    {
         System.out.println("fdjb");

        iMgr = ImageManager.getInstance();
        imgList = iMgr.readCurrent();
        System.out.println(imgList.get(0).toString());
        for (int i = 0; i < imgList.size(); ++i) {
            subfolders.add(i, imgList.get(i).getPath());
        }
         System.out.println(subfolders.get(index));
        final File[] files = new File("C:/info/images/" + subfolders.get(index)).listFiles();
        System.out.println(Arrays.toString(files));
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                images = new ArrayList<>();
                for (File file : files) {
                    if (file.isFile()) {
                        try {
                            images.add(ImageIO.read(new File(path + subfolders.get(index)+ "/" + file.getName().toString())));

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);

                JButton switchPic = new JButton("Switch");
                switchPic.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        currentPic++;
                        if (currentPic >= images.size()) {
                            currentPic = 0;
                        }
                        label.setIcon(new ImageIcon(images.get(currentPic)));
                    }
                });

                JFrame frame = new JFrame("Testing");
                frame.setUndecorated(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(label);
                frame.add(switchPic, BorderLayout.SOUTH);
                switchPic.doClick();
                frame.pack();
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
            }
        });
    }

}
